package com.ecom.ecommerse.orderline;

import com.ecom.ecommerse.exceptions.BusinessException;
import com.ecom.ecommerse.order.Order;
import com.ecom.ecommerse.order.OrderRepository;
import com.ecom.ecommerse.order.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class OrderLineServiceImpl implements OrderLineService{

    private final OrderLineRepository orderLineRepository;

    private final OrderRepository orderRepository;

    private final ModelMapper mapper;
    @Override
    public OrderLine saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = this.lineRequestToOrderLine(orderLineRequest);
        OrderLine orderLine1 = orderLineRepository.save(orderLine);
        return orderLineRepository.save(orderLine1);

    }

    @Override
    public List<OrderLineResponse> findByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(this::orderLineToResponse)
                .collect(Collectors.toList());
    }

    private OrderLine lineRequestToOrderLine(OrderLineRequest orderLineRequest){
        Order order = orderRepository.findById(orderLineRequest.getOrderId()).orElseThrow(() -> new BusinessException("order not found"));
        return OrderLine.builder()
                .order(order)
                .productId(orderLineRequest.getProductId())
                .quantity(orderLineRequest.getQuantity())
                .build();
    }

    private OrderLineResponse orderLineToResponse(OrderLine orderLine){
        return mapper.map(orderLine,OrderLineResponse.class);
    }


}
