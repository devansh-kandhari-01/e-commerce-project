package com.companyname.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleNotValidExcpetion(MethodArgumentNotValidException errors){
        List<String> errorList = errors.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException error){
        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductImgNotFoundException.class)
    public ResponseEntity<String> handleProductImgNotFound(ProductImgNotFoundException error){
        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOexception(IOException error){
        String msg = (error.getMessage() != null) ? error.getMessage() : "File upload failed.";
        return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception error){
        System.out.println(error.getMessage());
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
