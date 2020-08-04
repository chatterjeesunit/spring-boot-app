package com.dev.springdemo.customer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by sunitc on 4/19/18.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Audited
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streetAddress;
    private @NonNull String city;
    private @NonNull String stateCode;
    private @NonNull String country;
    private @NonNull String zipCode;

    @ManyToOne
    @JsonBackReference
    private Customer customer;

}
