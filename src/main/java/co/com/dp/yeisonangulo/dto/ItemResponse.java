package co.com.dp.yeisonangulo.dto;

import java.time.Instant;
import java.util.UUID;

public record ItemResponse(UUID id, String name, Instant createdAt) {
}
