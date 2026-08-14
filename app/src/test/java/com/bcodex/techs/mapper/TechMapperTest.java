package com.bcodex.techs.mapper;

import com.bcodex.techs.dto.response.TechResponse;
import com.bcodex.techs.entity.Category;
import com.bcodex.techs.entity.Tech;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechMapperTest {

    @Test
    void shouldMapTechToResponse() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        Tech entity = new Tech(
                "Spring Boot",
                "Java framework",
                category
        );

        TechResponse response =
                TechMapper.toResponse(entity);

        assertNotNull(response);

        assertEquals(
                entity.getName(),
                response.getName()
        );

        assertEquals(
                entity.getDescription(),
                response.getDescription()
        );

        assertEquals(
                category.getId(),
                response.getCategoryId()
        );

        assertEquals(
                category.getName(),
                response.getCategoryName()
        );

        assertNull(response.getId());
        assertNull(response.getCreatedAt());
        assertNull(response.getUpdatedAt());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {

        TechResponse response =
                TechMapper.toResponse(null);

        assertNull(response);
    }

    @Test
    void shouldMapCategoryInformation() {

        Category category = new Category(
                "Frontend",
                "Frontend technologies"
        );

        Tech tech = new Tech(
                "React",
                "Frontend library",
                category
        );

        TechResponse response =
                TechMapper.toResponse(tech);

        assertEquals(
                category.getName(),
                response.getCategoryName()
        );

        assertEquals(
                category.getId(),
                response.getCategoryId()
        );
    }
}