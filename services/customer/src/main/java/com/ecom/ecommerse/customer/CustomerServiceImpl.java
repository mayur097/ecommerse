package com.ecom.ecommerse.customer;

import com.ecom.ecommerse.customer.excptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private final ModelMapper mapper;
    @Override
    public String creteCustomer(CustomerRequest request) {
        Customer customer = this.dtoToCustomer(request);
        Customer customer1 = customerRepository.save(customer);
        return customer1.getId();
    }

    @Override
    public Customer updateCustomer(String custId, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(custId).orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + custId));
       if(customer.getAddress()!= null) {
           customer.setAddress(customerRequest.getAddress());
       }
       if(StringUtils.isNotBlank(customerRequest.getFirstname())) {
           customer.setFirstname(customerRequest.getFirstname());
       }
       if(StringUtils.isNotBlank(customerRequest.getLastname())) {
           customer.setLastname(customerRequest.getLastname());
       }
       if(StringUtils.isNotBlank(customer.getEmail())) {
           customer.setEmail(customerRequest.getEmail());
       }
        return customer;
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerRepository.findAll()
                 .stream()
                 .map(this::customerTOResponse)
                 .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getById(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + customerId));
        return customerTOResponse(customer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }


    Customer dtoToCustomer(CustomerRequest customerRequest){
        return mapper.map(customerRequest,Customer.class);
    }

    CustomerResponse customerTOResponse(Customer customer){
        return mapper.map(customer,CustomerResponse.class);
    }

}
