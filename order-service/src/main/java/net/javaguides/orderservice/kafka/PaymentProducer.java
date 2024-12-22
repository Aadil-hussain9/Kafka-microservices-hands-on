package net.javaguides.orderservice.kafka;

import net.javaguides.orderservice.dto.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProducer.class);
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;
    private final String paymentTopicName;

    public PaymentProducer(KafkaTemplate<String, PaymentEvent> kafkaTemplate, @Value("${spring.kafka.topic.payment}")String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.paymentTopicName = topic;
    }

    public void sendPaymentMessage(PaymentEvent event) {
        LOGGER.info(String.format("Sending Payment Event=> %s", event.toString()));

        Message<PaymentEvent> message =
                MessageBuilder.withPayload(event)
                        .setHeader(KafkaHeaders.TOPIC,paymentTopicName).build();
        kafkaTemplate.send(message);
    }
}
