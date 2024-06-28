package com.ecom.ecommerse.kafka;

import com.ecom.ecommerse.customer.CustomerResponse;
import com.ecom.ecommerse.order.PaymentMethod;
import com.ecom.ecommerse.product.PurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderConfirmation {
    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    CustomerResponse customer;
    List<PurchaseResponse> products;
}
