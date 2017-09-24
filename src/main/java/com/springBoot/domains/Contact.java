package com.springBoot.domains;

import io.jsonwebtoken.Claims;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.zip.DataFormatException;


/**
 * Created by Anita on 18-09-2017.
 */
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Phone.Number.Not.Null")
    @Size(min = 10, max = 15,message = "Minimum.size")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    private Long userId;
    private String name;

    Contact() {

    }

    public Contact(String phoneNumber, Long userId, String name) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.name = name;
    }

    public Contact(ContactVo contactVo) {
        phoneNumber = contactVo.phoneNumber;
        userId = contactVo.userId;
        name = contactVo.name;

    }

    public Contact(ContactVo contactVo, Long userIdAccess) {
        phoneNumber = contactVo.phoneNumber;
        userId = userIdAccess;
        name = contactVo.name;

    }


    public Contact(Claims claims) {
        this.userId = Long.parseLong(claims.get("userid").toString());


    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
