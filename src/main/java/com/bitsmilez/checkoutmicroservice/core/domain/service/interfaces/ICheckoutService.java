package com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.OrderDTO;

import java.util.UUID;

public interface ICheckoutService {

    // Create Checkout (CheckoutMessage)
    boolean createOrder(CheckoutMessage checkoutMessage);

    // GetLatestCheckout (UserID)
    OrderDTO getLatestCheckout(UUID userID);

    // GetAllCheckouts (UserID)
    OrderDTO getAllCheckouts(UUID userID);

}
