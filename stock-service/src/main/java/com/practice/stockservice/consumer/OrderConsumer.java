package com.practice.stockservice.consumer;

import com.practice.stockservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${order.queue.name}")
    private void consumeMessage(OrderEvent orderEvent) {
        logger.info(String.format("Order event received from the queue: %s", orderEvent.toString()));
    }
}
