package co.com.dp.yeisonangulo.kafka;

import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class KafkaItemEventProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaItemEventProducer kafkaItemEventPublisher;

    @BeforeEach
    void setUp() {
        kafkaItemEventPublisher = new KafkaItemEventProducer(kafkaTemplate, "items-topic");
    }

    @Test
    void shouldSendHelloWorldMessageToKafkaTopic() {
        UUID itemId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        kafkaItemEventPublisher.publishItemCreated(itemId);

        verify(kafkaTemplate).send("items-topic", "hello world: " + itemId);
    }
}
