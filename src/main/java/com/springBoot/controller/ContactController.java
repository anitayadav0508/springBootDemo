package com.springBoot.controller;

import com.springBoot.customException.ResourceNotFoundException;
import com.springBoot.domains.Contact;
import com.springBoot.domains.ContactVo;
import com.springBoot.repository.ContactRepository;
import com.springBoot.response.ApiResponse;
import com.springBoot.service.ContactService;
import com.springBoot.service.TokenProvider;
import com.springBoot.util.ValidationUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by Anita on 18-09-2017.
 */
@RestController
public class ContactController {

    private final Logger log = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationUtils validationUtils;

    @Autowired
    TokenProvider tokenProvider;
    ApiResponse response = new ApiResponse();

    @RequestMapping(value = "/user/{id}/Contacts", method = RequestMethod.GET)
    public List<Contact> getAll(HttpServletRequest request, @RequestHeader(value = "Authorization") String jwtToken, @PathVariable Long id) {
//        String jwtToken = (String) request.getHeader("Authorization");

        System.out.print("Token" + jwtToken);
        List<Contact> contactList = new ArrayList<Contact>();
        Long userIdAccess = null;

            Claims claims = tokenProvider.extractClaim(jwtToken);
            String role = claims.get("role").toString();
            userIdAccess = Long.parseLong(claims.get("userid").toString());
            if (id != userIdAccess) {
                throw new ResourceNotFoundException("The resource you are try to using is not exists  ", HttpStatus.FORBIDDEN);
            }
            if (role.equals("admin")) {
                contactList = contactRepository.findAll();
                for (Contact contact : contactList) {
                    System.out.println(contact);
                }

            } else if (role.equals("user") && (id == userIdAccess)) {

                contactList = contactRepository.findAllByUserId(userIdAccess);
                for (Contact contact : contactList) {
                    System.out.println(contact);
                }

            }
        return contactList;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/{id}/Contact")
    public Contact create(@Valid @RequestBody ContactVo contactVo,Errors errors, HttpServletRequest request, @PathVariable Long id, @RequestHeader(value = "Authorization") String jwtToken) {
//        String jwtToken = (String) request.getHeader("Authorization");
        System.out.println(errors);
        validationUtils.hasErrors(errors);
        System.out.print("Token" + jwtToken);
        Contact contact = null;
        Long userIdAccess=null;
        if (tokenProvider.validateToken(jwtToken)) {
            Claims claims = tokenProvider.extractClaim(jwtToken);
             userIdAccess = Long.parseLong(claims.get("userid").toString());
            if (userIdAccess == id) {
                Contact contact1 = new Contact(contactVo, userIdAccess);
                System.out.println(contact1.toString());
                contact = contactRepository.saveAndFlush(contact1);
            }else if (id != userIdAccess) {
                throw new ResourceNotFoundException("The resource you are try to using is not exists  ", HttpStatus.FORBIDDEN);
            }


        } else {
            throw new ResourceNotFoundException("Authentication failure", HttpStatus.FORBIDDEN);
        }

        return contact;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getContact/{id}")
    public Contact getSpecificContact(@PathVariable Long id) {
        Contact contact = contactRepository.findOne(id);
        if (contact == null) {
            throw new ResourceNotFoundException(id, "this Id not exists in Database", HttpStatus.NO_CONTENT);
        }


        return contact;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteContact/{id}")
    public void delete(@PathVariable Long id, HttpServletRequest request) {

        String jwtToken = (String) request.getHeader("Authorization");
        System.out.print("Token" + jwtToken);
        if (tokenProvider.validateToken(jwtToken)) {
            Claims claims = tokenProvider.extractClaim(jwtToken);
            Long userIdAccess = Long.parseLong(claims.get("userid").toString());
            Contact contact = contactRepository.findOne(id);
            if (contact == null) {
                throw new ResourceNotFoundException(id, "Id Not Exists", HttpStatus.NO_CONTENT);
            } else {
                contactRepository.deleteById(id);
                throw new ResourceNotFoundException(id, "Id Deleted Sucessfully", HttpStatus.OK);
            }
        } else {
            throw new ResourceNotFoundException("Authentication failure", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "user/{id}/Contact")
    public Contact update(@PathVariable Long id, @RequestBody ContactVo contact, HttpServletRequest request) {

        String jwtToken = (String) request.getHeader("Authorization");
        System.out.print("Token" + jwtToken);
        Claims claims = tokenProvider.extractClaim(jwtToken);
        Long userIdAccess = Long.parseLong(claims.get("userid").toString());
        Contact contactupdate = null;
        List<Contact> contactList = new ArrayList<Contact>();
        if (tokenProvider.validateToken(jwtToken)) {

            contactList = contactRepository.findAllByUserId(userIdAccess);

            System.out.println("**********************user list of contact******************");

            for (Contact checkContact : contactList) {
                System.out.println(checkContact.getPhoneNumber());
            }
            System.out.println("*****************end it here************************");


            for (Contact checkContact : contactList) {

                if (checkContact.getId() == id) {
                    contactupdate = contactRepository.findOne(id);

                } else {
                    throw new ResourceNotFoundException("No Content Found For updation", HttpStatus.NO_CONTENT);
                }

            }


            if (contactupdate != null) {
                contactupdate.setPhoneNumber(contact.getPhoneNumber());
            }

        } else {
            throw new ResourceNotFoundException("Authentication failure", HttpStatus.FORBIDDEN);
        }


        return contactRepository.save(contactupdate);


    }


}

