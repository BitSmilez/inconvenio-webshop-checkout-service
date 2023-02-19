package com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTO {
    private UUID productID;
    private Integer quantity;
}
