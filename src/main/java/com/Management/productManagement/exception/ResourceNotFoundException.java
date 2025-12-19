package com.Management.productManagement.exception;





// we made this excpetion when detail not found  in the exception


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException{


    // first  constructor for making the error meassge to display
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(resourceName + " not found with " + fieldName + " : " + fieldValue);
    }
    // second constructor to pass
    public ResourceNotFoundException(String message){
      super(message);
    }
}



