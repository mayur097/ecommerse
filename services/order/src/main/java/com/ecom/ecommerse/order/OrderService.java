package com.ecom.ecommerse.order;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    Integer createOrder(OrderRequest orderRequest);

    List<OrderResponse> getAll();

    OrderResponse findByOrderId(Integer orderId);
}
