package com.bitsmilez.checkoutmicroservice.port.mapper;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.Product;
import com.bitsmilez.checkoutmicroservice.core.domain.model.ProductID;
import com.bitsmilez.checkoutmicroservice.core.domain.model.WebOrder;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MapperTest {

    private static final UUID USER_ID = UUID.randomUUID();
    private  final UUID ORDER_ID = UUID.randomUUID();
    private static final String ADDRESS = "123 Main St.";
    private static final String CITY = "Anytown";
    private static final String ZIP = "12345";
    private  final String COUNTRY = "USA";
    private  final String PAYMENT_METHOD = "Credit Card";
    private  final String SHIPPING_METHOD = "Standard Shipping";
    private  final String FIRST_NAME = "John";
    private  final String LAST_NAME = "Doe";
    private  final String EMAIL = "johndoe@example.com";
    private  final String PHONE = "555-1234";
    private  final BigDecimal ORDER_TOTAL = BigDecimal.valueOf(50.00);

    private  CheckoutMessage checkoutMessage;

    @BeforeEach
    void setUp() {
        checkoutMessage = new CheckoutMessage();
        checkoutMessage.setUserID(String.valueOf(USER_ID));
        checkoutMessage.setAddress(ADDRESS);
        checkoutMessage.setCity(CITY);
        checkoutMessage.setZip(ZIP);
        checkoutMessage.setCountry(COUNTRY);
        checkoutMessage.setPaymentMethod(PAYMENT_METHOD);
        checkoutMessage.setShippingMethod(SHIPPING_METHOD);
        checkoutMessage.setFirstName(FIRST_NAME);
        checkoutMessage.setLastName(LAST_NAME);
        checkoutMessage.setEmail(EMAIL);
        checkoutMessage.setPhone(PHONE);
        checkoutMessage.setOrderTotal(ORDER_TOTAL);

        Product p1 = new Product();
        p1.setProductID(new ProductID(UUID.randomUUID(),ORDER_ID));
        Product p2 = new Product();
        p2.setProductID(new ProductID(UUID.randomUUID(),ORDER_ID));
        HashMap<String,Integer> products = new HashMap<>();
        products.put(p1.getProductID().getProductID().toString(),1);
        products.put(p2.getProductID().getProductID().toString(),1);
        checkoutMessage.setProducts((products));
    }

    @Test
    public void testToOrderDTO() {
        WebOrder webOrder = new WebOrder();
        webOrder.setUserID(USER_ID);
        webOrder.setOrderID(ORDER_ID);
        webOrder.setAddress(ADDRESS);
        webOrder.setCity(CITY);
        webOrder.setZip(ZIP);
        webOrder.setCountry(COUNTRY);
        webOrder.setPaymentMethod(PAYMENT_METHOD);
        webOrder.setShippingMethod(SHIPPING_METHOD);
        webOrder.setFirstName(FIRST_NAME);
        webOrder.setLastName(LAST_NAME);
        webOrder.setEmail(EMAIL);
        webOrder.setPhone(PHONE);
        webOrder.setOrderTotal(ORDER_TOTAL);
        String ORDER_DATE = "2023-02-21T10:30:00";
        webOrder.setOrderDate(ORDER_DATE);


        WebOrderDTO dto = Mapper.toOrderDTO(webOrder);

        assertNotNull(dto);
        assertEquals(webOrder.getOrderID(), dto.getOrderID());
        assertEquals(ADDRESS, dto.getAddress());
        assertEquals(CITY, dto.getCity());
        assertEquals(ZIP, dto.getZip());
        assertEquals(COUNTRY, dto.getCountry());
        assertEquals(PAYMENT_METHOD, dto.getPaymentMethod());
        assertEquals(SHIPPING_METHOD, dto.getShippingMethod());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(EMAIL, dto.getEmail());
        assertEquals(PHONE, dto.getPhone());
        assertEquals(ORDER_TOTAL, dto.getOrderTotal());
        assertEquals(ORDER_DATE, dto.getOrderDate());
    }

    @Test
    public void testToOrderEntity() {
        WebOrder entity = Mapper.toOrderEntity(checkoutMessage);

        assertNotNull(entity);
        assertEquals(ADDRESS, entity.getAddress());
        assertEquals(CITY, entity.getCity());
        assertEquals(ZIP, entity.getZip());
        assertEquals(COUNTRY, entity.getCountry());
        assertEquals(PAYMENT_METHOD, entity.getPaymentMethod());
        assertEquals(SHIPPING_METHOD, entity.getShippingMethod());
        assertEquals(FIRST_NAME, entity.getFirstName());
        assertEquals(LAST_NAME, entity.getLastName());
        assertEquals(EMAIL, entity.getEmail());
        assertEquals(PHONE, entity.getPhone());
        assertEquals(ORDER_TOTAL, entity.getOrderTotal());
        assertEquals(UUID.fromString(checkoutMessage.getUserID()), entity.getUserID());
    }

    @Test
    public void testToProductEntity() {
        UUID cartID = UUID.randomUUID();
        List<Product> products = Mapper.toProductEntity(checkoutMessage, cartID);

        assertNotNull(products);
        assertEquals(2, products.size());

        for (Product product : products) {
            assertNotNull(product.getProductID());
            assertEquals(cartID, product.getProductID().getOrderID());
            assertTrue(checkoutMessage.getProducts().containsKey(product.getProductID().getProductID().toString()));
            assertEquals(checkoutMessage.getProducts().get(product.getProductID().getProductID().toString()), product.getQuantity());
        }

    }
}