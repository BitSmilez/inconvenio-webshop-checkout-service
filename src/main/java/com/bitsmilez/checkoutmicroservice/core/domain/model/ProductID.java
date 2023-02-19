package com.bitsmilez.checkoutmicroservice.core.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductID {
    private UUID productID;
    private UUID orderID;
}
