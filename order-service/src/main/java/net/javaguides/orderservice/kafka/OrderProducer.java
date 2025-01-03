package net.javaguides.orderservice.kafka;
import net.javaguides.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private final String orderTopicName;

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(@Value("${spring.kafka.topic.order}") String orderTopicName, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.orderTopicName = orderTopicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<OrderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, orderTopicName)
                .build();
        kafkaTemplate.send(message);
    }
}
