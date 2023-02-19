package com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class WebOrderDTO {
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
    private String orderDate;
    private List <ProductDTO> products;

}
