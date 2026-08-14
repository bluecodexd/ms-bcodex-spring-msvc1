package com.bcodex.techs.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechTest {

    @Test
    void shouldCreateTechWithConstructor() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        Tech tech = new Tech(
                "Spring Boot",
                "Java framework",
                category
        );

        assertEquals("Spring Boot", tech.getName());
        assertEquals(
                "Java framework",
                tech.getDescription()
        );
        assertSame(category, tech.getCategory());
    }

    @Test
    void shouldSetAndGetProperties() {

        Tech tech = new Tech();

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        tech.setName("Spring Boot");
        tech.setDescription("Java framework");
        tech.setCategory(category);

        assertEquals("Spring Boot", tech.getName());
        assertEquals(
                "Java framework",
                tech.getDescription()
        );
        assertSame(category, tech.getCategory());
    }

    @Test
    void shouldAllowEmptyConstructor() {

        Tech tech = new Tech();

        assertNull(tech.getId());
        assertNull(tech.getName());
        assertNull(tech.getDescription());
        assertNull(tech.getCategory());
    }
}