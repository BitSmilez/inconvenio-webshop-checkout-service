package com.bitsmilez.checkoutmicroservice.core.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Product {
    @EmbeddedId
    private ProductID productID;
    Integer quantity;

}
