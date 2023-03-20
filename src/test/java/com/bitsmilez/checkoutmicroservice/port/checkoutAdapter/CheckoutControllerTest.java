package com.bitsmilez.checkoutmicroservice.port.checkoutAdapter;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.model.WebOrder;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.CheckoutServiceImpl;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.dto.WebOrderDTO;
import com.bitsmilez.checkoutmicroservice.port.mapper.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/application.properties" })
class CheckoutControllerTest {

    @Mock
    CheckoutServiceImpl checkoutService;
    @InjectMocks
    CheckoutController controllerUnderTest;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    private UUID userID;

    private WebOrderDTO webOrderDTO;

    @BeforeEach
    public void setup() {


        MockitoAnnotations.openMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();
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
        CheckoutMessage checkoutMessage = new CheckoutMessage(userID.toString(), address, city, zip, country, paymentMethod, shippingMethod, firstName, lastName, email, phone, orderTotal, products);
        WebOrder order = Mapper.toOrderEntity(checkoutMessage);
        order.setOrderID(orderID);
        webOrderDTO = Mapper.toOrderDTO(order);
        webOrderDTO.setProducts(products2);

    }

    @Test
    void getLastOrderUnknown() throws Exception {


        when(checkoutService.getLatestCheckout(userID)).thenReturn(    webOrderDTO);
        mockMvc.perform(get("/checkout/latest/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());

    }

    @Test
    void getLastOrder() throws Exception {
        when(checkoutService.getLatestCheckout(userID)).thenReturn(    webOrderDTO);

        MvcResult result = mockMvc.perform(get("/checkout/latest/" + userID))
                .andExpect(status().isOk()).andExpect(status().is(200)).andReturn();
        String content = result.getResponse().getContentAsString();
        WebOrderDTO webOrderDTO1 = objectMapper.readValue(content, WebOrderDTO.class);
        assertEquals(webOrderDTO, webOrderDTO1);


    }

    @Test
    void getAllCheckouts() throws Exception {
        WebOrderDTO webOrderDTO2 = new WebOrderDTO(UUID.randomUUID(), webOrderDTO.getAddress(), webOrderDTO.getCity(), webOrderDTO.getZip(), webOrderDTO.getCountry(), webOrderDTO.getPaymentMethod(), webOrderDTO.getShippingMethod(), webOrderDTO.getFirstName(), webOrderDTO.getLastName(), webOrderDTO.getEmail(), webOrderDTO.getPhone(), webOrderDTO.getOrderTotal(), webOrderDTO.getOrderDate(), webOrderDTO.getProducts());
        List<WebOrderDTO> webOrderDTOList = new ArrayList<>();
        webOrderDTOList.add(webOrderDTO);
        webOrderDTOList.add(webOrderDTO2);
        when(checkoutService.getAllCheckouts(userID)).thenReturn(webOrderDTOList);
        mockMvc.perform(get("/checkout/all/" + userID))
                .andExpect(status().isOk()).andExpect(status().is(200)).andExpect(content().json(objectMapper.writeValueAsString(webOrderDTOList)));
    }

    @Test
    void getAllCheckoutsUnknown() throws Exception {
        when(checkoutService.getAllCheckouts(userID)).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/checkout/all/"+UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }


}