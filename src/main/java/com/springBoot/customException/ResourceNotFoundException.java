package com.springBoot.customException;

import org.springframework.http.HttpStatus;

/**
 * Created by Anita on 19-09-2017.
 */
public class ResourceNotFoundException extends RuntimeException {
    private Long resourceId;
    public HttpStatus statusCode;

    public ResourceNotFoundException(Long resourceId, String message, HttpStatus statusCode) {

        super(message);
        System.out.println("2222222222222222222222");
        System.out.println("2222222222222222222222");
        System.out.println("2222222222222222222222");
        System.out.println("2222222222222222222222");
        this.resourceId = resourceId;
        this.statusCode = statusCode;
        System.out.println(this.statusCode);
    }

    public ResourceNotFoundException(String message,HttpStatus statusCode) {

        super(message);
        this.statusCode=statusCode;

    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
