package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFound;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/getalldetails")
    public Page<Customer> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findAll(pageable);
    }

    @GetMapping("/getdetailById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Customer not exist with id:" + id));
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customerDetails) {
        Customer updateCustomer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Customer not exist with id: " + id));

        updateCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
        updateCustomer.setEmail(customerDetails.getEmail());
        updateCustomer.setAddress(customerDetails.getAddress());
        updateCustomer.setAccounts(customerDetails.getAccounts());

        customerRepository.save(updateCustomer);
        return ResponseEntity.ok(updateCustomer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("Customer not exist with id: " + id));

        customerRepository.delete(customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
