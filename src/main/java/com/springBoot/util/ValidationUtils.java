package com.springBoot.util;

import com.springBoot.customException.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Locale;

/**
 * Created by Anita on 24-09-2017.
 */
@Component
public class ValidationUtils {

    @Autowired
    MessageSource messageSource;

    public void hasErrors(Errors errors){
        if(errors.hasErrors()){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(errors.getFieldErrors().get(0));
            throw new ValidationException(messageSource.getMessage(errors.getFieldErrors().get(0).getDefaultMessage(),null, Locale.ENGLISH), HttpStatus.BAD_REQUEST);
        }
    }
}
