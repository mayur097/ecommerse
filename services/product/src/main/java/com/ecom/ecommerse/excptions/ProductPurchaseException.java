package com.ecom.ecommerse.excptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductPurchaseException extends RuntimeException {
    public ProductPurchaseException(String mesg) {
    }
}
