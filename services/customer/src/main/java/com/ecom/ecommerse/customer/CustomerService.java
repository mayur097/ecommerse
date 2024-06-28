package com.ecom.ecommerse.customer;

import java.util.List;

public interface CustomerService {
    String creteCustomer(CustomerRequest request);

    Customer updateCustomer(String custId, CustomerRequest customerRequest);

    List<CustomerResponse> getAll();

    CustomerResponse getById(String customerId);

    void deleteCustomer(String customerId);
}
