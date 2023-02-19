package com.bitsmilez.checkoutmicroservice.port.mapper;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.Order;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.OrderDTO;

public class Mapper {

    // checkoutMessageToOrderEntity / DTO
    public OrderDTO toOrderDTO(CheckoutMessage checkoutMessage) {

       OrderDTO dto = new OrderDTO();
       dto.setAddress(checkoutMessage.getAddress());
       dto.setCity(checkoutMessage.getCity());
       dto.setZip(checkoutMessage.getZip());
       dto.setCountry(checkoutMessage.getCountry());
       dto.setPaymentMethod(checkoutMessage.getPaymentMethod());
       dto.setShippingMethod(checkoutMessage.getShippingMethod());
       dto.setFirstName(checkoutMessage.getFirstName());
       dto.setLastName(checkoutMessage.getLastName());
       dto.setEmail(checkoutMessage.getEmail());
       dto.setPhone(checkoutMessage.getPhone());
       dto.setOrderTotal(checkoutMessage.getOrderTotal());

       return dto;
    }

    public Order toOrderEntity(OrderDTO orderDTO, boolean orderID, boolean orderDate) {

        Order entity = new Order();
        if (orderID){
           entity.setOrderID(orderDTO.getOrderID());
        }

        if (orderDate){
           entity.setOrderDate(orderDTO.getOrderDate());
        }

        entity.setAddress(orderDTO.getAddress());
        entity.setCity(orderDTO.getCity());
        entity.setZip(orderDTO.getZip());
        entity.setCountry(orderDTO.getCountry());
        entity.setPaymentMethod(orderDTO.getPaymentMethod());
        entity.setShippingMethod(orderDTO.getShippingMethod());
        entity.setFirstName(orderDTO.getFirstName());
        entity.setLastName(orderDTO.getLastName());
        entity.setEmail(orderDTO.getEmail());
        entity.setPhone(orderDTO.getPhone());
        entity.setOrderTotal(orderDTO.getOrderTotal());

        return entity;
    }


}
