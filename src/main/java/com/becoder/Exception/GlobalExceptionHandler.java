package com.becoder.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception){
//        log.error("GlobalExceptionHandler::handleException::", exception.getMessage());
        return  new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(NullPointerException.class)
public ResponseEntity<?> handleNullPointerException(Exception exception){
//        log.error("GlobalExceptionHandler::handleNullPointerException::", exception.getMessage());
        return  new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
}


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException exception){
//        log.error("GlobalExceptionHandler::handleResourceNotFoundException::", exception.getMessage());
        return  new ResponseEntity<>(exception.getError(), HttpStatus.BAD_REQUEST);
    }



}
