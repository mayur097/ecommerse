package com.ecom.ecommerse.kafka.order;

import com.ecom.ecommerse.kafka.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {

    private String orderReference;

    private BigDecimal totalAmount;

    private PaymentMethod paymentMethod;

    private Customer customer;

    private List<Products> products;
}
