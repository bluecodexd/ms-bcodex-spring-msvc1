package com.bcodex.techs.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    static class TestEntity extends BaseEntity {
    }

    @Test
    void shouldSetCreatedAtAndUpdatedAtOnCreate() {

        TestEntity entity = new TestEntity();

        assertNull(entity.getCreatedAt());
        assertNull(entity.getUpdatedAt());

        entity.onCreate();

        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getUpdatedAt());

        assertEquals(
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Test
    void shouldUpdateUpdatedAtOnUpdate() throws InterruptedException {

        TestEntity entity = new TestEntity();

        entity.onCreate();

        LocalDateTime originalCreatedAt =
                entity.getCreatedAt();

        LocalDateTime originalUpdatedAt =
                entity.getUpdatedAt();

        Thread.sleep(10);

        entity.onUpdate();

        assertEquals(
                originalCreatedAt,
                entity.getCreatedAt()
        );

        assertNotNull(entity.getUpdatedAt());

        assertTrue(
                entity.getUpdatedAt()
                        .isAfter(originalUpdatedAt)
        );
    }
}