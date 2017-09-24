package com.springBoot.controller;

import com.springBoot.customException.ResourceNotFoundException;
import com.springBoot.customException.ValidationException;
import com.springBoot.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anita on 19-09-2017.
 */
@ControllerAdvice
@RestController
public class ExceptionHandlingController {



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException ex) {
        ApiResponse response = new ApiResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorCode(ex.statusCode);
        return new ResponseEntity<ApiResponse>(response, ex.statusCode);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse> resourceNotFound(ValidationException ex) {
        ApiResponse response = new ApiResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorCode(ex.statusCode);
        return new ResponseEntity<ApiResponse>(response, ex.statusCode);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> resourceNotFound(HttpRequestMethodNotSupportedException ex) {
        ApiResponse response = new ApiResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorCode(HttpStatus. METHOD_NOT_ALLOWED);
        return new ResponseEntity<ApiResponse>(response, response.getErrorCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> resourceNotFound(HttpMessageNotReadableException ex) {
        ApiResponse response = new ApiResponse();
        response.setErrorMessage(" Bad Request! Request Body Missing");
        response.setErrorCode(HttpStatus. BAD_REQUEST);
        return new ResponseEntity<ApiResponse>(response, response.getErrorCode());
    }

 /*   @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<ApiResponse> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiResponse response = new ApiResponse();
        response.setErrorMessage(ex.getLocalizedMessage());
        response.setErrorCode(HttpStatus. BAD_REQUEST);
        return new ResponseEntity<ApiResponse>(response, response.getErrorCode());
    }
*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> resourceNotFound(MethodArgumentNotValidException ex) {
        ApiResponse response = new ApiResponse();
        response.setErrorMessage(ex.getLocalizedMessage());
        response.setErrorCode(HttpStatus. BAD_REQUEST);
        return new ResponseEntity<ApiResponse>(response, response.getErrorCode());
    }



}

