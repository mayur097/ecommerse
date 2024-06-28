package com.ecom.ecommerse.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Validated  // when we use valid in request body and there is relation always use
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String id;

    @NotNull(message = "Firstname is required")
    private String firstname;

    @NotNull(message = "Lastname is required")
    private String lastname;

    @NotNull(message = "Email is required")
    @Email(message = "The customer is not correctly formatted")
    private String email;
}
