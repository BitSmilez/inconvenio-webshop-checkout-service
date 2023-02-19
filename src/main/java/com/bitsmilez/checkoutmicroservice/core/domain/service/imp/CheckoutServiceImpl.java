package com.bitsmilez.checkoutmicroservice.core.domain.service.imp;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.ICheckoutService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckoutServiceImpl implements ICheckoutService {


    @Override
    public void createOrder(CheckoutMessage checkoutMessage) {
        // TODO: Implement
    }

    @Override
    public void getLatestCheckout(UUID userID) {

    }

    @Override
    public void getAllCheckouts(UUID userID) {

    }
}
