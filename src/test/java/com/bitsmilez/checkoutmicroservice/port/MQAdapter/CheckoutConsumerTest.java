package com.bitsmilez.checkoutmicroservice.port.MQAdapter;

import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.core.domain.service.imp.CheckoutServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckoutConsumerTest {
    @Mock
    CheckoutServiceImpl checkoutService;

    @InjectMocks
    CheckoutConsumer checkoutConsumer;



    @Test
    void receiveCheckoutMessage() {
        when(checkoutService.createOrder(any(CheckoutMessage.class))).thenReturn(false);
        Integer res = checkoutConsumer.receiveCheckoutMessage(new CheckoutMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), res);
        verify(checkoutService, times(1)).createOrder(any(CheckoutMessage.class));

    }

    @Test
    void receiveCheckoutMessage2() {
        when(checkoutService.createOrder(any(CheckoutMessage.class))).thenReturn(true);
        Integer res = checkoutConsumer.receiveCheckoutMessage(new CheckoutMessage());
        assertEquals(HttpStatus.OK.value(), res);
        verify(checkoutService, times(1)).createOrder(any(CheckoutMessage.class));

    }






}