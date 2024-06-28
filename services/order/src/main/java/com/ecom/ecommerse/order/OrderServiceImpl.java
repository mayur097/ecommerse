package com.ecom.ecommerse.order;

import com.ecom.ecommerse.customer.CustomerClient;
import com.ecom.ecommerse.customer.CustomerResponse;
import com.ecom.ecommerse.exceptions.BusinessException;
import com.ecom.ecommerse.exceptions.OrderNotFoundException;
import com.ecom.ecommerse.kafka.OrderConfirmation;
import com.ecom.ecommerse.kafka.OrderProducer;
import com.ecom.ecommerse.orderline.OrderLineRequest;
import com.ecom.ecommerse.orderline.OrderLineService;
import com.ecom.ecommerse.payment.PaymentClient;
import com.ecom.ecommerse.payment.PaymentRequest;
import com.ecom.ecommerse.product.ProductClient;
import com.ecom.ecommerse.product.PurchaseRequest;
import com.ecom.ecommerse.product.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final PaymentClient paymentClient;

    private final OrderRepository orderRepository;

    private final OrderLineService orderLineService;

    private final ModelMapper mapper;

    private final OrderProducer orderProducer;

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        //check coustomer-> customer service  //Openfign
        CustomerResponse customerResponse = customerClient.findCustomerById(orderRequest.getCustomerId())
                .orElseThrow(() -> new BusinessException("Customer not found with id" + orderRequest.getCustomerId()));


        // purchase product --> product service  //restTemplate
        List<PurchaseResponse> productList = productClient.purchaseProduct(orderRequest.getProducts());


        UUID uuid = UUID.randomUUID();
        String ref = uuid.toString();
        // persists order
        Order order = this.requestToOrder(orderRequest);
        order.setReference(ref);
        Order order1 = orderRepository.save(order);

        // persists order lines
        for (PurchaseRequest purchaseRequest : orderRequest.getProducts()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order1.getId(),
                            purchaseRequest.getProductId(),
                            purchaseRequest.getQuantity()
                    )
            );

        }
        // start payment
        paymentClient.requestOrderPayment(new PaymentRequest(
                order1.getTotalAmount(),
                order1.getPaymentMethod(),
                order1.getId(),
                order1.getReference(),
                customerResponse
        ));

        // send order confirmation --> notification - ms
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                customerResponse,
                productList

        ));
        return order.getId();
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::orderToOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("order not found with id " + orderId));
        return this.orderToOrderResponse(order);
    }

    private Order requestToOrder(OrderRequest orderRequest){

        return Order.builder()
                .customerId(orderRequest.getCustomerId())
                .paymentMethod(orderRequest.getPaymentMethod())
                .totalAmount(orderRequest.getTotalAmount())
                .build();
    }

    private OrderResponse orderToOrderResponse(Order order){
        return mapper.map(order,OrderResponse.class);
    }


}
