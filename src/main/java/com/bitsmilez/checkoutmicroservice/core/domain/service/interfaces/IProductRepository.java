package com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces;

import com.bitsmilez.checkoutmicroservice.core.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByProductID_OrderID(UUID orderID);
}
