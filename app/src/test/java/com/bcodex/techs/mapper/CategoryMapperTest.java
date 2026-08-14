package com.bcodex.techs.mapper;

import com.bcodex.techs.dto.response.CategoryResponse;
import com.bcodex.techs.entity.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    @Test
    void shouldMapCategoryToResponse() {

        Category entity = new Category(
                "Backend",
                "Backend technologies"
        );

        LocalDateTime createdAt =
                LocalDateTime.of(2026, 8, 10, 10, 0);

        LocalDateTime updatedAt =
                LocalDateTime.of(2026, 8, 10, 11, 0);

        CategoryResponse response =
                CategoryMapper.toResponse(entity);

        assertNotNull(response);

        assertEquals(
                entity.getName(),
                response.getName()
        );

        assertEquals(
                entity.getDescription(),
                response.getDescription()
        );

        // BaseEntity no tiene setters para timestamps.
        // Se validan cuando son asignados por JPA/lifecycle.
        assertNull(response.getId());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {

        CategoryResponse response =
                CategoryMapper.toResponse(null);

        assertNull(response);
    }
}