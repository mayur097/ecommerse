package com.ecom.ecommerse.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerResponse {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
