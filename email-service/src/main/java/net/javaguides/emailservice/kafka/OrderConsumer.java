package net.javaguides.emailservice.kafka;

import net.javaguides.emailservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    // Listener for Order Events
    @KafkaListener(
            topics = "${spring.kafka.topic.order}",
            groupId = "${spring.kafka.consumer.order-group-id}"
    )
    public void consume(
            @Payload OrderEvent event,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {

        LOGGER.info(String.format("Order event received from partition %d, offset %d => %s", partition, offset, event.toString()));
        // Send an email to the customer or other processing logic
    }
}

