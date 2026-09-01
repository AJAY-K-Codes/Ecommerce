package com.project.Ecommerce.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgumentValidException(MethodArgumentNotValidException e)
    {   Map<String,String> response=new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err->{
            String fieldname=((FieldError)err).getField();
            String fieldmessage=err.getDefaultMessage();
            response.put(fieldname,fieldmessage);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e){
         String status =e.getMessage();
         return new ResponseEntity<>(status,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> myResouceAlreadyFound(APIException e){
        String status =e.getMessage();
        return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
    }

}
