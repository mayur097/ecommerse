package com.ecom.ecommerse.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductPurchaseRequest {

    @NotNull(message = "product is mandatory")
   private Integer productId;

    @NotNull(message = "quantity is mandatory")
    private double quantity;
}
