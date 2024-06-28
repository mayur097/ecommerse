package com.ecom.ecommerse.orderline;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderLineService {

    OrderLine saveOrderLine(OrderLineRequest orderLineRequest);
    List<OrderLineResponse> findByOrderId(Integer orderId);
}
