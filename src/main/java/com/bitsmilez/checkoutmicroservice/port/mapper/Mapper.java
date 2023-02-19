package com.bitsmilez.checkoutmicroservice.port.mapper;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.WebOrder;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class Mapper {

    // checkoutMessageToOrderEntity / DTO
    public static WebOrderDTO toOrderDTO(WebOrder webOrder) {
        WebOrderDTO dto = new WebOrderDTO();
        dto.setOrderID(webOrder.getOrderID());
        dto.setAddress(webOrder.getAddress());
        dto.setCity(webOrder.getCity());
        dto.setZip(webOrder.getZip());
        dto.setCountry(webOrder.getCountry());
        dto.setPaymentMethod(webOrder.getPaymentMethod());
        dto.setShippingMethod(webOrder.getShippingMethod());
        dto.setFirstName(webOrder.getFirstName());
        dto.setLastName(webOrder.getLastName());
        dto.setEmail(webOrder.getEmail());
        dto.setPhone(webOrder.getPhone());
        dto.setOrderTotal(webOrder.getOrderTotal());
        dto.setOrderDate(webOrder.getOrderDate());
        return dto;

    }

    public static WebOrder toOrderEntity(CheckoutMessage checkoutMessage) {
        WebOrder entity = new WebOrder();
        return getOrder(entity, checkoutMessage.getAddress(), checkoutMessage.getCity(), checkoutMessage.getZip(), checkoutMessage.getCountry(), checkoutMessage.getPaymentMethod(), checkoutMessage.getShippingMethod(), checkoutMessage.getFirstName(), checkoutMessage.getLastName(), checkoutMessage.getEmail(), checkoutMessage.getPhone(), checkoutMessage.getOrderTotal(), UUID.fromString(checkoutMessage.getUserID()));
    }

    private static WebOrder getOrder(WebOrder webOrder, String address, String city, String zip, String country, String paymentMethod, String shippingMethod, String firstName, String lastName, String email, String phone, BigDecimal orderTotal, UUID userID) {
        webOrder.setUserID(userID);
        webOrder.setAddress(address);
        webOrder.setCity(city);
        webOrder.setZip(zip);
        webOrder.setCountry(country);
        webOrder.setPaymentMethod(paymentMethod);
        webOrder.setShippingMethod(shippingMethod);
        webOrder.setFirstName(firstName);
        webOrder.setLastName(lastName);
        webOrder.setEmail(email);
        webOrder.setPhone(phone);
        webOrder.setOrderTotal(orderTotal);
        return webOrder;
    }


}
