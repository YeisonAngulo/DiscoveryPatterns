package co.com.dp.yeisonangulo.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateItemRequest(
        @NotBlank(message = "name must not be blank")
        String name
) {
}
