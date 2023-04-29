package com.abhiprojects.blog.blogappapis.exceptions;


import com.abhiprojects.blog.blogappapis.payloads.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto resourceNotFoundException(ResourceNotFoundException ex)
    {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String,String>> MethodArgumentNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String,String> resp=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError) error).getField();
            String message=((FieldError) error).getDefaultMessage();
            resp.put(fieldName,message);
        });
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }

}
