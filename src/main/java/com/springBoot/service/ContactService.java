package com.springBoot.service;

import com.springBoot.customException.ResourceNotFoundException;
import com.springBoot.domains.ContactVo;
import com.springBoot.repository.ContactRepository;
import com.springBoot.domains.Contact;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Anita on 18-09-2017.
 */
@Component
public class ContactService {
  /*  @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private TokenProvider tokenProvider;


    public List<Contact> toGetAllRecord(String jwtToken, Long id) {
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

    public Contact saveContact(String jwtToken, Long id, ContactVo contactVo) {

        Contact contact = null;
        Long userIdAccess = null;

        Claims claims = tokenProvider.extractClaim(jwtToken);
        userIdAccess = Long.parseLong(claims.get("userid").toString());
        if (userIdAccess == id) {
            Contact contact1 = new Contact(contactVo, userIdAccess);
            System.out.println(contact1.toString());
            contact = contactRepository.saveAndFlush(contact1);
        } else if (id != userIdAccess) {
            throw new ResourceNotFoundException(" the current user does not have access to perform the requested action!", HttpStatus.FORBIDDEN);
        }


        return contact;
    }*/
}

