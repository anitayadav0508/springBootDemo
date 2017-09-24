package com.springBoot.repository;

import com.springBoot.domains.Contact;
import com.springBoot.response.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anita on 18-09-2017.
 */

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Transactional
    void deleteById(Long id);

    //List<Contact> findAll();

   // Contact findOne(Long id);

    Contact save(Contact persisted);

    Contact saveAndFlush(Contact persisted);

    List<Contact> findAllByUserId(Long userId);
    Contact findByUserId(Long userId);







}