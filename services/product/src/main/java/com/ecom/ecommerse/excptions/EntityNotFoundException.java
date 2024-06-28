package com.ecom.ecommerse.excptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityNotFoundException extends RuntimeException {
    private final String msg;
//    public EntityNotFoundException(String msg) {
//        super(msg);
//    }
}
