package com.bitsmilez.checkoutmicroservice.core.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderID;
    private String address;
    private String city;
    private String zip;
    private String country;
    private String paymentMethod;
    private String shippingMethod;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal orderTotal;
    private String orderDate = java.time.Instant.now().toString();

}
