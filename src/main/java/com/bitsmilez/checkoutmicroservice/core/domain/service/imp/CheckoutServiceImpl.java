package com.bitsmilez.checkoutmicroservice.core.domain.service.imp;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.Product;
import com.bitsmilez.checkoutmicroservice.core.domain.model.WebOrder;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.ICheckoutService;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.IOrderRepository;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.IProductRepository;
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
    @Autowired
    IProductRepository productRepository;

    public CheckoutServiceImpl(IOrderRepository orderRepository, IProductRepository productRepository) {
        super();
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    @Override
    public boolean createOrder(CheckoutMessage checkoutMessage) {
        WebOrder webOrder = Mapper.toOrderEntity(checkoutMessage);
        try {
            UUID orderID = orderRepository.save(webOrder).getOrderID();
             productRepository.saveAll(Mapper.toProductEntity(checkoutMessage, orderID));
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
        WebOrderDTO order = Mapper.toOrderDTO(orders.get(0));
        List<Product> products = (productRepository.findByProductID_OrderID(order.getOrderID()));
        products.forEach(product -> order.addProduct(product.getProductID().getProductID(), product.getQuantity()));
        return (order);

    }

    @Override
    public List<WebOrderDTO> getAllCheckouts(UUID userID) {
        List<WebOrder> orders = orderRepository.findAllByUserIDOrderByOrderDateDesc(userID);
        if (orders.isEmpty()) {
            return new ArrayList<>();
        }
        List<WebOrderDTO> orderDTOList = orders.stream().map(Mapper::toOrderDTO).toList();
        for (WebOrderDTO order : orderDTOList) {
            List<Product> products = (productRepository.findByProductID_OrderID(order.getOrderID()));
            products.forEach(product -> order.addProduct(product.getProductID().getProductID(), product.getQuantity()));

        }
        return orderDTOList;
    }


}
