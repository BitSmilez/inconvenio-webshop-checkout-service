package com.bitsmilez.checkoutmicroservice.port.MQAdapter;


import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.config.MQConfig.MQConfig;
import com.bitsmilez.checkoutmicroservice.core.domain.service.interfaces.ICheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CheckoutConsumer {


    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutConsumer.class);
    private final ICheckoutService checkoutService;

    public CheckoutConsumer(ICheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }


    @RabbitListener(queues = MQConfig.CHECKOUT_QUEUE)
    public Integer receiveCheckoutMessage(CheckoutMessage message) {

        LOGGER.info("Received message: {}", message);
        if (checkoutService.createOrder(message)){
            return HttpStatus.OK.value();
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

    }
}
