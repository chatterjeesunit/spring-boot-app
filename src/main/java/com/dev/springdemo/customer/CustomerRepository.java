package com.dev.springdemo.customer;

import com.dev.springdemo.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by sunitc on 4/19/18.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //Contains search on either firstname or lastname
    List<Customer> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    Optional<Customer> findCustomerByEmailAddress(String email);
}
