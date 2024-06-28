package com.ecom.ecommerse.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotNull(message = "product name is required")
    private String name;
    @NotNull(message = "product description is required")
    private String descriptions;
    @NotNull(message = "product quantity should be positive")
    private double availableQuantity;
    @NotNull(message = "product price should be positive")
    private BigDecimal price;

}
