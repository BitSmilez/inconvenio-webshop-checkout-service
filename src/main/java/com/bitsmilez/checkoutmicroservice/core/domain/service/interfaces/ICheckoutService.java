package com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;

import java.util.UUID;

public interface ICheckoutService {

    // Create Checkout (CheckoutMessage)
    void createOrder(CheckoutMessage checkoutMessage);

    // GetLatestCheckout (UserID)
    void getLatestCheckout(UUID userID);

    // GetAllCheckouts (UserID)
    void getAllCheckouts(UUID userID);

}
