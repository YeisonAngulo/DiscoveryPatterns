package co.com.dp.yeisonangulo.kafka;

import java.util.UUID;

public final class ItemEventMessages {

    private static final String ITEM_CREATED_PREFIX = "hello world: ";

    private ItemEventMessages() {
    }

    public static String itemCreated(UUID itemId) {
        return ITEM_CREATED_PREFIX + itemId;
    }
}
