package net.javaguides.emailservice.kafka;

import net.javaguides.emailservice.dto.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.payment}",
            groupId = "${spring.kafka.consumer.payment-group-id}"
    )
    public void consume(PaymentEvent event) {
        LOGGER.info(String.format("Payment event received => %s", event.toString()));
    }
}

