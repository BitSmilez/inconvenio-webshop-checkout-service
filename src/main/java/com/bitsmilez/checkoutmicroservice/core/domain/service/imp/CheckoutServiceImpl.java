package com.bitsmilez.checkoutmicroservice.core.domain.service.imp;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.WebOrder;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.ICheckoutService;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.IOrderRepository;
import com.bitsmilez.checkoutmicroservice.port.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckoutServiceImpl implements ICheckoutService {
    @Autowired
    IOrderRepository orderRepository;

    public CheckoutServiceImpl(IOrderRepository orderRepository) {
        super();
        this.orderRepository = orderRepository;
    }


    @Override
    public boolean createOrder(CheckoutMessage checkoutMessage) {
        WebOrder webOrder = Mapper.toOrderEntity(checkoutMessage);
        orderRepository.save(webOrder);
        return true;


    }



    @Override
    public WebOrderDTO getLatestCheckout(UUID userID) {
        return null;

    }

    @Override
    public WebOrderDTO getAllCheckouts(UUID userID) {
        return null;

    }
}
