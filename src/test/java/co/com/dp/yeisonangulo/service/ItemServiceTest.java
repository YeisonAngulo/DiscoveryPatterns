package co.com.dp.yeisonangulo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.dp.yeisonangulo.domain.Item;
import co.com.dp.yeisonangulo.dto.ItemResponse;
import co.com.dp.yeisonangulo.kafka.ItemEventPublisher;
import co.com.dp.yeisonangulo.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemEventPublisher itemEventPublisher;

    @Captor
    private ArgumentCaptor<Item> itemCaptor;

    private ItemService itemService;

    @BeforeEach
    void setUp() {
        Clock fixedClock = Clock.fixed(Instant.parse("2026-03-12T18:00:00Z"), ZoneOffset.UTC);
        itemService = new ItemService(itemRepository, itemEventPublisher, fixedClock);
        when(itemRepository.save(any(Item.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void shouldCreateAndStoreItemAndPublishKafkaMessage() {
        ItemResponse response = itemService.createItem("Example item");

        verify(itemRepository, times(1)).save(itemCaptor.capture());
        verify(itemEventPublisher, times(1)).publishItemCreated(response.id());

        Item savedItem = itemCaptor.getValue();

        assertNotNull(response.id());
        assertEquals("Example item", response.name());
        assertEquals(Instant.parse("2026-03-12T18:00:00Z"), response.createdAt());

        assertEquals(response.id(), savedItem.id());
        assertEquals("Example item", savedItem.name());
        assertEquals(Instant.parse("2026-03-12T18:00:00Z"), savedItem.createdAt());
    }
}
