package com.ecom.ecommerse.handler;


import com.ecom.ecommerse.exceptions.BusinessException;
import com.ecom.ecommerse.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleEntityNotFoundException(BusinessException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMsg());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(OrderNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error->{
                    String field = ((FieldError) error).getField();
                    String defaultMessage = error.getDefaultMessage();
                    errors.put(field,defaultMessage);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handle(BindException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error->{
                    String field = ((FieldError) error).getField();
                    String defaultMessage = error.getDefaultMessage();
                    errors.put(field,defaultMessage);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }

}
