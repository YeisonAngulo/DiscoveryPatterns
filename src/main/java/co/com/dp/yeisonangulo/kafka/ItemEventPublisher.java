package co.com.dp.yeisonangulo.kafka;

import java.util.UUID;

public interface ItemEventPublisher {
    void publishItemCreated(UUID itemId);
}
