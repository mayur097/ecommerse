package com.ecom.ecommerse.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        return ResponseEntity.ok(customerService.creteCustomer(request));
    }

    @PutMapping("/{custId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String custId,
                                                   @Valid CustomerRequest customerRequest){
        return ResponseEntity.accepted().body(customerService.updateCustomer(custId,customerRequest));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String customerId ){
        return ResponseEntity.ok(customerService.getById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
