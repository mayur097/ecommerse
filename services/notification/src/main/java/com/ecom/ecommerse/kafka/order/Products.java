package com.ecom.ecommerse.kafka.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;
}
