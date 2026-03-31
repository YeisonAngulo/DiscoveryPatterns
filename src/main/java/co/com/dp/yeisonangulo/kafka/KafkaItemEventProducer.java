package co.com.dp.yeisonangulo.kafka;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaItemEventProducer implements ItemEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(KafkaItemEventProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public KafkaItemEventProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            @Value("${app.kafka.topic}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void publishItemCreated(UUID itemId) {
        String message = ItemEventMessages.itemCreated(itemId);
        log.info("Publishing message to topic {}: {}", topic, message);
        kafkaTemplate.send(topic, message);
    }
}
