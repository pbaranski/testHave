package com.vhealth.api.utils;


import com.vhealth.api.utils.exceptions.InvalidPayloadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.PersistenceException;

@Controller
public class ExceptionsResolver {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity handleIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        return new ResponseEntity("Error occurred - probably resource was not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity handlePersistanceException(PersistenceException ex) {
        return new ResponseEntity("Error occurred - probably because of bad payload", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity handleIllegalStateException() {
        return new ResponseEntity("Error occurred - probably referring to nonexistent resource - state", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException() {
        return new ResponseEntity("Error occurred - probably referring to nonexistent resource", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidPayloadException.class)
    public ResponseEntity handleInvalidPayloadException(InvalidPayloadException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity(ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity("Could not read JSON", HttpStatus.BAD_REQUEST);
    }


}