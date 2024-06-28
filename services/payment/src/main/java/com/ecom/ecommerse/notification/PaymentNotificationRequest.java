package com.ecom.ecommerse.notification;

import com.ecom.ecommerse.payment.PaymentMethod;
import com.ecom.ecommerse.payment.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentNotificationRequest {

  private  String orderReference;

  private BigDecimal amount;

  private PaymentMethod paymentMethod;

  private String customerFirstName;

  private String customerLastname;

  private String customerEmail;


}
