package com.bcodex.techs.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void shouldCreateCategoryWithConstructor() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        assertEquals("Backend", category.getName());
        assertEquals(
                "Backend technologies",
                category.getDescription()
        );
        assertNotNull(category.getTechs());
        assertTrue(category.getTechs().isEmpty());
    }

    @Test
    void shouldSetAndGetProperties() {

        Category category = new Category();

        category.setName("Frontend");
        category.setDescription("Frontend technologies");

        assertEquals("Frontend", category.getName());
        assertEquals(
                "Frontend technologies",
                category.getDescription()
        );
    }

    @Test
    void shouldSetAndGetTechs() {

        Category category = new Category();

        ArrayList<Tech> techs = new ArrayList<>();

        Tech tech = new Tech(
                "React",
                "Frontend library",
                category
        );

        techs.add(tech);

        category.setTechs(techs);

        assertEquals(1, category.getTechs().size());
        assertSame(tech, category.getTechs().get(0));
    }

    @Test
    void shouldReturnSameTechsList() {

        Category category = new Category();

        assertNotNull(category.getTechs());

        assertTrue(
                category.getTechs().isEmpty()
        );
    }
}
