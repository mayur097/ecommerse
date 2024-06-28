package com.ecom.ecommerse.order;

import com.ecom.ecommerse.product.PurchaseRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {
//    @Positive(message = "order amount should be positive")
    private BigDecimal totalAmount;
//    @Positive(message = "payment method should be precised")
    private PaymentMethod paymentMethod;
    @NotNull(message = "customer should be present")
    @NotEmpty
    @NotBlank
    private String customerId;
    List<PurchaseRequest> products;
}
