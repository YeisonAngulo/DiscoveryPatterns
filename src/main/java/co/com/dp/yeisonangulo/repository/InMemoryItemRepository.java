package co.com.dp.yeisonangulo.repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import co.com.dp.yeisonangulo.domain.Item;

@Repository
public class InMemoryItemRepository implements ItemRepository {

    private final Map<UUID, Item> storage = new ConcurrentHashMap<>();

    @Override
    public Item save(Item item) {
        storage.put(item.id(), item);
        return item;
    }
}
