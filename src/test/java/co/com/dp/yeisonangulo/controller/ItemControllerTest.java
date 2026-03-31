package co.com.dp.yeisonangulo.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import co.com.dp.yeisonangulo.dto.ItemResponse;
import co.com.dp.yeisonangulo.exception.ItemExceptionHandler;
import co.com.dp.yeisonangulo.service.ItemService;

import static org.mockito.Mockito.when;

@WebMvcTest(ItemController.class)
@Import(ItemExceptionHandler.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    void shouldCreateItemWhenRequestIsValid() throws Exception {
        UUID itemId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        Instant createdAt = Instant.parse("2026-03-30T18:00:00Z");

        when(itemService.createItem(eq("Example item")))
                .thenReturn(new ItemResponse(itemId, "Example item", createdAt));

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Example item"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(itemId.toString()))
                .andExpect(jsonPath("$.name").value("Example item"))
                .andExpect(jsonPath("$.createdAt").value("2026-03-30T18:00:00Z"));
    }

    @Test
    void shouldReturnBadRequestWhenNameIsBlank() throws Exception {
        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "   "
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors.name").value("name must not be blank"));
    }

    @Test
    void shouldReturnBadRequestWhenNameIsMissing() throws Exception {
        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors.name").value("name must not be blank"));
    }
}
