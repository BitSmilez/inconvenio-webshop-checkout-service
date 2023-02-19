package com.bitsmilez.checkoutmicroservice.core.domain.service.imp;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.Order;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.OrderDTO;
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
        Order order = Mapper.toOrderEntity(checkoutMessage);
        orderRepository.save(order);
        return true;


    }



    @Override
    public OrderDTO getLatestCheckout(UUID userID) {
        return null;

    }

    @Override
    public OrderDTO getAllCheckouts(UUID userID) {
        return null;

    }
}
