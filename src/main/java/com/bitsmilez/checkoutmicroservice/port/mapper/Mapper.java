package com.bitsmilez.checkoutmicroservice.port.mapper;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.Order;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.OrderDTO;

import java.math.BigDecimal;

public class Mapper {

    // checkoutMessageToOrderEntity / DTO
    public static OrderDTO toOrderDTO(CheckoutMessage checkoutMessage) {

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

    public static Order toOrderEntity(OrderDTO orderDTO, boolean orderID, boolean orderDate) {

        Order entity = new Order();
        if (orderID){
           entity.setOrderID(orderDTO.getOrderID());
        }

        if (orderDate){
           entity.setOrderDate(orderDTO.getOrderDate());
        }

        return getOrder(entity, orderDTO.getAddress(), orderDTO.getCity(), orderDTO.getZip(), orderDTO.getCountry(),
                orderDTO.getPaymentMethod(), orderDTO.getShippingMethod(), orderDTO.getFirstName(), orderDTO.getLastName(), orderDTO.getEmail(), orderDTO.getPhone(), orderDTO.getOrderTotal());
    }

    public static Order toOrderEntity(CheckoutMessage checkoutMessage) {
        Order entity = new Order();
        return getOrder(entity, checkoutMessage.getAddress(), checkoutMessage.getCity(), checkoutMessage.getZip(), checkoutMessage.getCountry(), checkoutMessage.getPaymentMethod(), checkoutMessage.getShippingMethod(), checkoutMessage.getFirstName(), checkoutMessage.getLastName(), checkoutMessage.getEmail(), checkoutMessage.getPhone(), checkoutMessage.getOrderTotal());
    }

    private static Order getOrder(Order order, String address, String city, String zip, String country, String paymentMethod, String shippingMethod, String firstName, String lastName, String email, String phone, BigDecimal orderTotal) {
        order.setAddress(address);
        order.setCity(city);
        order.setZip(zip);
        order.setCountry(country);
        order.setPaymentMethod(paymentMethod);
        order.setShippingMethod(shippingMethod);
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setEmail(email);
        order.setPhone(phone);
        order.setOrderTotal(orderTotal);
        return order;
    }


}
