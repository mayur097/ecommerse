package com.ecom.ecommerse.orderline;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class OrderLineRequest {

    private Integer orderId;
    private Integer productId;
    private Double quantity;

    public OrderLineRequest(Integer orderId, Integer productId, Double quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
