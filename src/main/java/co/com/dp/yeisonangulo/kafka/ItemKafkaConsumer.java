package co.com.dp.yeisonangulo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ItemKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(ItemKafkaConsumer.class);

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        log.info("Received Kafka message: {}", message);
    }
}
