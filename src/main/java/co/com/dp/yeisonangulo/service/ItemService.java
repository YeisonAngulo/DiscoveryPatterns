package co.com.dp.yeisonangulo.service;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.com.dp.yeisonangulo.domain.Item;
import co.com.dp.yeisonangulo.dto.ItemResponse;
import co.com.dp.yeisonangulo.kafka.ItemEventPublisher;
import co.com.dp.yeisonangulo.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemEventPublisher itemEventPublisher;
    private final Clock clock;

    public ItemService(ItemRepository itemRepository, ItemEventPublisher itemEventPublisher, Clock clock) {
        this.itemRepository = itemRepository;
        this.itemEventPublisher = itemEventPublisher;
        this.clock = clock;
    }

    public ItemResponse createItem(String name) {
        UUID itemId = UUID.randomUUID();
        Instant createdAt = Instant.now(clock);

        Item item = new Item(itemId, name, createdAt);
        itemRepository.save(item);
        itemEventPublisher.publishItemCreated(itemId);

        return new ItemResponse(item.id(), item.name(), item.createdAt());
    }
}
