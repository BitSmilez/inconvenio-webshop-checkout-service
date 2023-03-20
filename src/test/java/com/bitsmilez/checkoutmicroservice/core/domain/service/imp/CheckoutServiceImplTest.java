package com.bitsmilez.checkoutmicroservice.core.domain.service.imp;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.Product;
import com.bitsmilez.checkoutmicroservice.core.domain.model.ProductID;
import com.bitsmilez.checkoutmicroservice.core.domain.model.WebOrder;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.ICheckoutService;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.IOrderRepository;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.checkoutmicroservice.port.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CheckoutServiceImplTest {

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private IProductRepository productRepository;

    private ICheckoutService checkoutService;

    private UUID userID;
    private UUID orderID;
    private CheckoutMessage checkoutMessage;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        checkoutService = new CheckoutServiceImpl(orderRepository, productRepository);
        UUID userID = UUID.randomUUID();
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
        WebOrderDTO webOrderDTO = Mapper.toOrderDTO(order);
        webOrderDTO.setProducts(products2);

    }

    @Test
    void createOrderShouldReturnTrue() {

        WebOrder webOrder = Mapper.toOrderEntity(checkoutMessage);
        when(orderRepository.save(any(WebOrder.class))).thenReturn(webOrder);
        when(productRepository.saveAll(any())).thenReturn(null);
        assertTrue(checkoutService.createOrder(checkoutMessage));
        verify(orderRepository, times(1)).save(any(WebOrder.class));
        verify(productRepository, times(1)).saveAll(any());
    }

    @Test
    void createOrderShouldReturnFalse() {



        when(orderRepository.save(any(WebOrder.class))).thenThrow(RuntimeException.class);
        assertFalse(checkoutService.createOrder(checkoutMessage));
        verify(orderRepository, times(1)).save(any(WebOrder.class));
        verify(productRepository, never()).saveAll(any());
    }

    @Test
    void getLatestCheckoutShouldReturnNull() {
        UUID userID = UUID.randomUUID();
        when(orderRepository.findAllByUserIDOrderByOrderDateDesc(userID)).thenReturn(new ArrayList<>());
        assertNull(checkoutService.getLatestCheckout(userID));
        verify(orderRepository, times(1)).findAllByUserIDOrderByOrderDateDesc(userID);
        verify(productRepository, never()).findByProductID_OrderID(any());
    }

    @Test
    void getLatestCheckoutShouldReturnWebOrderDTO() {
        WebOrder webOrder = Mapper.toOrderEntity(checkoutMessage);
        webOrder.setOrderID(orderID);
        WebOrderDTO webOrderDTO = Mapper.toOrderDTO(webOrder);
        HashMap<UUID,Integer> products = new HashMap<>();
        Product product = new Product();
        product.setProductID(new ProductID(UUID.randomUUID(),orderID));
        products.put(product.getProductID().getProductID(), product.getQuantity());

        Product product1 = new Product();
        product1.setProductID(new ProductID(UUID.randomUUID(),orderID));
        products.put(product1.getProductID().getProductID(), product1.getQuantity());


        webOrderDTO.setProducts(products);

        when(orderRepository.findAllByUserIDOrderByOrderDateDesc(userID)).thenReturn(Collections.singletonList(webOrder));
        when(productRepository.findByProductID_OrderID(any())).thenReturn(Arrays.asList(product, product1));

        assertEquals(webOrderDTO, checkoutService.getLatestCheckout(userID));
        verify(orderRepository, times(1)).findAllByUserIDOrderByOrderDateDesc(userID);
        verify(productRepository, times(1)).findByProductID_OrderID(any());
    }

    @Test
    void getAllCheckoutShouldReturnNull() {
        when(orderRepository.findAllByUserIDOrderByOrderDateDesc(any())).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(),checkoutService.getAllCheckouts(orderID));
        verify(orderRepository, times(1)).findAllByUserIDOrderByOrderDateDesc(any());
        verify(productRepository, never()).findByProductID_OrderID(any());
    }

    @Test
    void getAllCheckoutShouldReturnList() {
        WebOrder webOrder = Mapper.toOrderEntity(checkoutMessage);
        webOrder.setOrderID(orderID);
        WebOrderDTO webOrderDTO = Mapper.toOrderDTO(webOrder);
        HashMap<UUID,Integer> products = new HashMap<>();
        Product product = new Product();
        product.setProductID(new ProductID(UUID.randomUUID(),orderID));
        products.put(product.getProductID().getProductID(), product.getQuantity());

        Product product1 = new Product();
        product1.setProductID(new ProductID(UUID.randomUUID(),orderID));
        products.put(product1.getProductID().getProductID(), product1.getQuantity());

        webOrderDTO.setProducts(products);
        when(orderRepository.findAllByUserIDOrderByOrderDateDesc(any())).thenReturn(Collections.singletonList(webOrder));
        when(productRepository.findByProductID_OrderID(any())).thenReturn(Arrays.asList(product, product1));
        assertEquals(Collections.singletonList(webOrderDTO),checkoutService.getAllCheckouts(orderID));


    }


}
