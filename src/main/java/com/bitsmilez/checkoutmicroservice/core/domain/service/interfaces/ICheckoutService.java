package com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;

public interface ICheckoutService {

    // Create Checkout (CheckoutMessage)
    void createOrder(CheckoutMessage checkoutMessage);

    // GetLatestCheckout (UserID)

    // GetAllCheckouts (UserID)

}
