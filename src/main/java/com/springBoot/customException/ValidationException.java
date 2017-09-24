package com.springBoot.customException;

import org.springframework.http.HttpStatus;

/**
 * Created by Anita on 19-09-2017.
 */
public class ValidationException extends RuntimeException {
    public HttpStatus statusCode;

    public ValidationException(String message, HttpStatus statusCode) {

        super(message);
        this.statusCode = statusCode;
    }

    public ValidationException(String message){
        super(message);
    }
}
