package com.bitsmilez.checkoutmicroservice.port.MQAdapter;


import com.bitsmilez.checkoutmicroservice.config.MQConfig.CheckoutMessage;
import com.bitsmilez.checkoutmicroservice.config.MQConfig.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutConsumer {


    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutConsumer.class);


    @RabbitListener(queues = MQConfig.CHECKOUT_QUEUE)
    public void receiveCheckoutMessage(CheckoutMessage message) {
        LOGGER.info("Received message: {}", message);
    }
}
