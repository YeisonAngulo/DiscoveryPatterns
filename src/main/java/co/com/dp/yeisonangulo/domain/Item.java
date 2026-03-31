package co.com.dp.yeisonangulo.domain;

import java.time.Instant;
import java.util.UUID;

public record Item(UUID id, String name, Instant createdAt) {
}
