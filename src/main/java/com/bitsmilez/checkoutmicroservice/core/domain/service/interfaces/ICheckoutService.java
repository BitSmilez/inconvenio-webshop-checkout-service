package com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;

import java.util.List;
import java.util.UUID;

public interface ICheckoutService {

    // Create Checkout (CheckoutMessage)
    boolean createOrder(CheckoutMessage checkoutMessage);

    // GetLatestCheckout (UserID)
    WebOrderDTO getLatestCheckout(UUID userID);

    // GetAllCheckouts (UserID)
    List<WebOrderDTO> getAllCheckouts(UUID userID);

}
