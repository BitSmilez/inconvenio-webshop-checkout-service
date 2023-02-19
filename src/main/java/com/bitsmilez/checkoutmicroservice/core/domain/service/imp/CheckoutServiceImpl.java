package com.bitsmilez.checkoutmicroservice.core.domain.service.imp;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.WebOrder;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.ICheckoutService;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.IOrderRepository;
import com.bitsmilez.checkoutmicroservice.port.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        try {
            orderRepository.save(webOrder);
            return true;
        } catch (Exception e) {
            return false;
        }



    }



    @Override
    public WebOrderDTO getLatestCheckout(UUID userID) {
        List<WebOrder> orders = orderRepository.findAllByUserIDOrderByOrderDateDesc(userID);
        if (orders.isEmpty()){
            return null;
        }
        WebOrder order = orders.get(0);
        return Mapper.toOrderDTO(order);

    }

    @Override
    public List<WebOrderDTO> getAllCheckouts(UUID userID) {
            List <WebOrder> orders = orderRepository.findAllByUserIDOrderByOrderDateDesc(userID);
            if (orders.isEmpty()){
                return new ArrayList<>();
            }
            return orders.stream().map(Mapper::toOrderDTO).toList();

        }



}
