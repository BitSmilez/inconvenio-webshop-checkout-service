package com.bitsmilez.checkoutmicroservice.core.domain.service.imp;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.Product;
import com.bitsmilez.checkoutmicroservice.core.domain.model.WebOrder;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.IOrderRepository;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.checkoutmicroservice.port.mapper.Mapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class CheckoutServiceImplTest {
    @Mock
    private IProductRepository productRepository;
    @Mock
    private IOrderRepository orderRepository;

    @InjectMocks
    private CheckoutServiceImpl checkoutService;
    private UUID userID;
    private WebOrderDTO webOrderDTO;
    private CheckoutMessage checkoutMessage;

    CheckoutServiceImplTest() {
    }

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        userID = UUID.randomUUID();
        UUID orderID = UUID.randomUUID();
        String address = "Test address";
        String city = "Test city";
        String zip = "1234";
        String country = "Test country";
        String paymentMethod = "Test payment method";
        String shippingMethod = "Test shipping method";
        String firstName = "Test first name";
        String lastName = "Test last name";
        String email = "Test email";
        String phone = "Test phone";
        BigDecimal orderTotal = new BigDecimal(1234);
        UUID productID = UUID.randomUUID();
        UUID productID2 = UUID.randomUUID();
        Map<String, Integer> products = new HashMap<>();
        products.put(productID.toString(), 1);
        products.put(productID2.toString(), 2);

        HashMap<UUID,Integer> products2 = new HashMap<>();
        products2.put(productID, 1);
        products2.put(productID2, 2);
         checkoutMessage = new CheckoutMessage(userID.toString(), address, city, zip, country, paymentMethod, shippingMethod, firstName, lastName, email, phone, orderTotal, products);
        WebOrder order = Mapper.toOrderEntity(checkoutMessage);
        order.setOrderID(orderID);
        webOrderDTO = Mapper.toOrderDTO(order);
        webOrderDTO.setProducts(products2);
    }


    @Test
    void createOrder() {
        WebOrder webOrder = Mapper.toOrderEntity(checkoutMessage);

        when(orderRepository.save(any(WebOrder.class))).thenReturn(webOrder);
        when(productRepository.saveAll((anyList()))).thenReturn(Mapper.toProductEntity(checkoutMessage,webOrder.getOrderID()));
        boolean result = checkoutService.createOrder(checkoutMessage);
        assertTrue(result);
    }
}