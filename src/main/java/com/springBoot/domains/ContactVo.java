package com.springBoot.domains;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Anita on 19-09-2017.
 */
public class ContactVo {


//    @NotNull(message = "Phone.Number.Not.Null")
    @NotNull(message = "Phone No must Required")
//    @Size(min = 10, max = 15,message = "Minimum.size")
    @Size(min = 10, max = 15,message = "Minimum size 10 and 15")
    String phoneNumber;

    @NotNull(message = "user Id cannot Be Null")
    Long userId;
    String name;

    public ContactVo(){

    }
    public ContactVo(String phoneNumber, Long userId, String name) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.name= name;
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
}
