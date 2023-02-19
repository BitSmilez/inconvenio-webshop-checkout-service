package com.bitsmilez.checkoutmicroservice.config.MQConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String CHECKOUT_QUEUE = "checkout-queue";

    public static final String CHECKOUT_EXCHANGE = "checkout_exchange";

    public static final String CHECKOUT_TOPIC = "checkoutservice.createCheckout";



    @Bean
    Queue checkoutQueue() {
        return new Queue(CHECKOUT_QUEUE, false);
    }


    @Bean
    TopicExchange checkoutExchange() {
        return new TopicExchange(CHECKOUT_EXCHANGE);
    }

    @Bean
    Binding checkoutBinding(Queue checkoutQueue, TopicExchange checkoutExchange) {
        return BindingBuilder.bind(checkoutQueue).to(checkoutExchange).with(CHECKOUT_TOPIC);
    }


    @Bean
    public MessageConverter productMessageJsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate productTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(productMessageJsonConverter());
        return template;
    }


}
