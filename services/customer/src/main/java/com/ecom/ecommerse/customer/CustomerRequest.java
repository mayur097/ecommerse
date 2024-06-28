package com.ecom.ecommerse.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomerRequest {
    @NotNull(message = "Customer firstname is required")
    private String firstname;
    @NotNull(message = "Customer lastname is required")
    private String lastname;
    @NotNull(message = "Customer email is required")
    @Email(message = "customer email is not valid")
    private String email;
    private Address address;
}
