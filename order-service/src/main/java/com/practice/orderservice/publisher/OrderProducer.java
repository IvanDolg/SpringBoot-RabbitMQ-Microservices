package com.practice.orderservice.publisher;

import com.practice.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    @Value("${order.exchange.name}")
    private String exchange;

    @Value("${order.routing.key}")
    private String routingKey;

    @Value("${email.routing.key}")
    private String emailRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderEvent orderEvent) {
        logger.info(String.format("Order event sent to RabbitMQ: %s", orderEvent.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, orderEvent);
        rabbitTemplate.convertAndSend(exchange, emailRoutingKey, orderEvent);
    }

}
