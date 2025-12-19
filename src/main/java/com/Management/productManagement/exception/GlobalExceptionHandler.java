package com.Management.productManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;




//  all in one controller
@RestControllerAdvice

// matched with my exception and after that it call our exception also help if bhut sare annotation  ho to ek  try catch nhi bana padta



public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(
            ResourceNotFoundException ex,
            WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false),
                "RESOURCE_NOT_FOUND"
        );
        // ResponseEntity :) box of error contain body (ex.getMessage()) and status code  (HttpStatus.Status)

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }




    // making Another exception which handle other exception dude(500)
    // Exception.class father of all exception dude
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAnyException(
            Exception ex,
            WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
