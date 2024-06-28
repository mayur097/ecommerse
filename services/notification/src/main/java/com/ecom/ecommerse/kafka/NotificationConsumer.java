package com.ecom.ecommerse.kafka;

import com.ecom.ecommerse.email.EmailService;
import com.ecom.ecommerse.notification.NotificationRepository;
import com.ecom.ecommerse.notification.Notification;
import com.ecom.ecommerse.kafka.order.OrderConfirmation;
import com.ecom.ecommerse.kafka.payment.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.ecom.ecommerse.notification.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation){
        log.info("--consuming msg from payment topic {}",paymentConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        //to do send email
        String customerName = paymentConfirmation.getCustomerFirstname() + " " + paymentConfirmation.getCustomerLastname();
        emailService.sentPaymentSuccessEmail(
                paymentConfirmation.getCustomerEmail(),
                customerName,
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderReference()
        );



    }


    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation){
        log.info("--consuming msg from order topic {}",orderConfirmation);
        notificationRepository.save(
                Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
//                        .paymentConfirmation(paymentConfirmation)
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        //to do send email
        String customerName = orderConfirmation.getCustomer().getFirstname()+ " " + orderConfirmation.getCustomer().getLastname();
        emailService.sentOrderConfirmationEmail(
                orderConfirmation.getCustomer().getEmail(),
                customerName,
                orderConfirmation.getTotalAmount(),
                orderConfirmation.getOrderReference(),
                orderConfirmation.getProducts()

        );


    }
}
