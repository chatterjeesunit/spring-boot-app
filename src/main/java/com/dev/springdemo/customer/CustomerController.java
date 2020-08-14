package com.dev.springdemo.customer;

import com.dev.springdemo.ErrorMessage;
import com.dev.springdemo.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

/**
 * Created by sunitc on 4/19/18.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerDetail(@PathVariable(name = "id") String customerId) {
        try {
            Long customerIdLong = Long.valueOf(customerId);
            Customer customer = customerService.getCustomerById(customerIdLong)
                    .orElseThrow(()->new RuntimeException("Customer does not exists"));
            return ResponseEntity.ok(customer);
        }catch(Exception ex) {
            return handleException(ex);
        }
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchCustomerByEmail(
                @RequestParam(name = "email") String emailAddress) {
        try {
            Optional<Customer> customer = customerService.findByEmail(emailAddress);
            return ResponseEntity.ok(customer);
        }catch(Exception ex) {
            return handleException(ex);
        }
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCustomers(
            @RequestParam("pageNum") String pageNumber,
            @RequestParam("pageSize") String pageSize) {
        try {
            Integer pageNumberLong = Integer.valueOf(pageNumber);
            Integer pageSizeLong = Integer.valueOf(pageSize);
            //Create a new paginated search request.
            PageRequest pageRequest = PageRequest.of(pageNumberLong, pageSizeLong);
            Page page = customerService.findAll(pageRequest);
            return ResponseEntity.ok(page.getContent());
        }catch(Exception ex) {
            return handleException(ex);
        }
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.create(customer);
            return ResponseEntity.created(new URI("/customer/" + createdCustomer.getId())).body(customer);
        }catch(Exception ex) {
            return handleException(ex);
        }
    }


    @PutMapping (path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCustomer(@PathVariable(name = "id") String customerId,  @RequestBody Customer customer) {
        try {
            customer.setId(Long.valueOf(customerId));
            Customer updatedCustomer = customerService.update(customer);
            return ResponseEntity.ok(updatedCustomer);
        }catch(Exception ex) {
            return handleException(ex);
        }
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") String customerId) {
        try {
            customerService.deleteCustomer(Long.valueOf(customerId));
            return ResponseEntity.noContent().build();
        }catch(Exception ex) {
            return handleException(ex);
        }
    }

    private ResponseEntity<ErrorMessage> handleException(Exception ex) {
        ex.printStackTrace();
        ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
