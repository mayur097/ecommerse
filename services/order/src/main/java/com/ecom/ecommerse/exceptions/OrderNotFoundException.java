package com.ecom.ecommerse.exceptions;

import lombok.Data;

@Data
public class OrderNotFoundException extends RuntimeException{

    private String msg;
   public OrderNotFoundException(String msg){
        super(msg);
    }

    public OrderNotFoundException() {
    }
}
