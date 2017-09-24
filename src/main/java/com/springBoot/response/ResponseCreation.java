package com.springBoot.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Anita on 19-09-2017.
 */
public class ResponseCreation  {
    String message;
    Integer value;

    public ResponseCreation(){

    }
  public  ResponseCreation(String message,Integer value){

        this.value=value;
        this.message=message;
    }

}
