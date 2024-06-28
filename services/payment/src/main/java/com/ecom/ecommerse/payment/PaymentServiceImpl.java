package com.ecom.ecommerse.payment;

import com.ecom.ecommerse.notification.NotificationProducer;
import com.ecom.ecommerse.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final ModelMapper mapper;

    private final NotificationProducer notificationProducer;
    @Override
    public Integer createPayment(PaymentRequest paymentRequest) {
        Payment payment = this.requestToPayment(paymentRequest);
        Payment payment1 = paymentRepository.save(payment);

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.getOrderReference(),
                        paymentRequest.getAmount(),
                        paymentRequest.getPaymentMethod(),
                        paymentRequest.getCustomer().getFirstname(),
                        paymentRequest.getCustomer().getLastname(),
                        paymentRequest.getCustomer().getEmail()

                )
        );

        return payment1.getId();
    }

    Payment requestToPayment(PaymentRequest paymentRequest){
        return mapper.map(paymentRequest,Payment.class);
    }
}
