package com.bitsmilez.checkoutmicroservice.port.mapper;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.WebOrder;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;

import java.math.BigDecimal;

public class Mapper {

    // checkoutMessageToOrderEntity / DTO
    public static WebOrderDTO toOrderDTO(CheckoutMessage checkoutMessage) {

       WebOrderDTO dto = new WebOrderDTO();
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

    public static WebOrder toOrderEntity(WebOrderDTO webOrderDTO, boolean orderID, boolean orderDate) {

        WebOrder entity = new WebOrder();
        if (orderID){
           entity.setOrderID(webOrderDTO.getOrderID());
        }

        if (orderDate){
           entity.setOrderDate(webOrderDTO.getOrderDate());
        }

        return getOrder(entity, webOrderDTO.getAddress(), webOrderDTO.getCity(), webOrderDTO.getZip(), webOrderDTO.getCountry(),
                webOrderDTO.getPaymentMethod(), webOrderDTO.getShippingMethod(), webOrderDTO.getFirstName(), webOrderDTO.getLastName(), webOrderDTO.getEmail(), webOrderDTO.getPhone(), webOrderDTO.getOrderTotal());
    }

    public static WebOrder toOrderEntity(CheckoutMessage checkoutMessage) {
        WebOrder entity = new WebOrder();
        return getOrder(entity, checkoutMessage.getAddress(), checkoutMessage.getCity(), checkoutMessage.getZip(), checkoutMessage.getCountry(), checkoutMessage.getPaymentMethod(), checkoutMessage.getShippingMethod(), checkoutMessage.getFirstName(), checkoutMessage.getLastName(), checkoutMessage.getEmail(), checkoutMessage.getPhone(), checkoutMessage.getOrderTotal());
    }

    private static WebOrder getOrder(WebOrder webOrder, String address, String city, String zip, String country, String paymentMethod, String shippingMethod, String firstName, String lastName, String email, String phone, BigDecimal orderTotal) {
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
