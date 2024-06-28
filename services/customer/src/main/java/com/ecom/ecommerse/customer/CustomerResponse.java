package com.ecom.ecommerse.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse implements Serializable {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
