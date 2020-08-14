package com.dev.springdemo.customer;

import com.dev.springdemo.customer.model.Customer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by sunitc on 4/19/18.
 */
@Service
@Log4j2
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Optional<Customer> getCustomerById(Long customerId) {
        log.info("Fetching customer by id: {}", customerId);
        return customerRepository.findById(customerId);
    }

    public Customer create(Customer customer) {
        log.info("Creating a new customer with emailAddress: {}", customer.getEmailAddress());
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        log.info("Updating a customer with id: {}", customer.getId());
        Optional<Customer> optionalCustomer = customerRepository.findById(customer.getId());
        if(optionalCustomer.isEmpty()) {
            throw new RuntimeException("Cannot find the customer by id " + customer.getId());
        }
        Customer existingCustomer = optionalCustomer.get();
        existingCustomer.setAddresses(customer.getAddresses());
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        return customerRepository.save(existingCustomer);
    }


    public List<Customer> findByName(String name){
        return customerRepository.findAllByFirstNameContainingOrLastNameContaining(name, name);
    }

    public Optional<Customer> findByEmail(String email){
        return customerRepository.findCustomerByEmailAddress(email);
    }

    //Paging implementation of findAll
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
