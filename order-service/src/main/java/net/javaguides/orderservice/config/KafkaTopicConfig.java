package net.javaguides.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.List;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.payment}")
    private String paymentTopicName;

    @Value("${spring.kafka.topic.order}")
    private String orderTopicName;

    public String getPaymentTopicName() {
        return paymentTopicName;
    }

    public String getOrderTopicName() {
        return orderTopicName;
    }


    // spring bean for kafka topic
    @Bean
    public NewTopic orderTopic(){
        return TopicBuilder.name(getOrderTopicName())
                .partitions(3)
                .replicas(2)
                .config(TopicConfig.RETENTION_MS_CONFIG,"3000") // 3 seconds in milliseconds
                .build();
    }

    // spring bean for kafka topic payment done
    @Bean
    public NewTopic paymentTopic(){
        return TopicBuilder.name(getPaymentTopicName())
                .build();
    }
}
