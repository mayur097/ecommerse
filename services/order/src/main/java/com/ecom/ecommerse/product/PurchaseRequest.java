package com.ecom.ecommerse.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    @NotNull(message = "Product is mandatory")
    private Integer productId;

    @Positive(message = "quantity is mandatory")
    private double quantity;
}
