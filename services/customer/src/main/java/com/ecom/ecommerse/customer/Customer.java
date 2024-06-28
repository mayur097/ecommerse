package com.ecom.ecommerse.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Customer implements Serializable {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;
}
