package com.bcodex.techs.service.impl;

import com.bcodex.techs.dto.request.CreateCategoryRequest;
import com.bcodex.techs.dto.request.UpdateCategoryRequest;
import com.bcodex.techs.dto.response.CategoryResponse;
import com.bcodex.techs.entity.Category;
import com.bcodex.techs.exception.ResourceNotFoundException;
import com.bcodex.techs.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void shouldFindAllCategories() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        when(categoryRepository.findAll())
                .thenReturn(List.of(category));

        List<CategoryResponse> result =
                categoryService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        assertEquals(
                "Backend",
                result.get(0).getName()
        );

        assertEquals(
                "Backend technologies",
                result.get(0).getDescription()
        );

        verify(categoryRepository)
                .findAll();
    }

    @Test
    void shouldFindCategoryById() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        CategoryResponse result =
                categoryService.findById(1L);

        assertNotNull(result);

        assertEquals(
                "Backend",
                result.getName()
        );

        assertEquals(
                "Backend technologies",
                result.getDescription()
        );

        verify(categoryRepository)
                .findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotFound() {

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> categoryService.findById(1L)
        );

        verify(categoryRepository)
                .findById(1L);
    }

    @Test
    void shouldCreateCategory() {

        CreateCategoryRequest request =
                new CreateCategoryRequest();

        request.setName("Frontend");
        request.setDescription(
                "Frontend technologies"
        );

        when(categoryRepository.existsByName("Frontend"))
                .thenReturn(false);

        Category savedCategory = new Category(
                "Frontend",
                "Frontend technologies"
        );

        when(categoryRepository.save(any(Category.class)))
                .thenReturn(savedCategory);

        CategoryResponse result =
                categoryService.create(request);

        assertNotNull(result);

        assertEquals(
                "Frontend",
                result.getName()
        );

        assertEquals(
                "Frontend technologies",
                result.getDescription()
        );

        verify(categoryRepository)
                .existsByName("Frontend");

        verify(categoryRepository)
                .save(any(Category.class));
    }

    @Test
    void shouldRejectDuplicateCategory() {

        CreateCategoryRequest request =
                new CreateCategoryRequest();

        request.setName("Backend");
        request.setDescription(
                "Backend technologies"
        );

        when(categoryRepository.existsByName("Backend"))
                .thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> categoryService.create(request)
        );

        verify(categoryRepository)
                .existsByName("Backend");

        verify(categoryRepository, never())
                .save(any(Category.class));
    }

    @Test
    void shouldUpdateCategory() {

        Category category = new Category(
                "Backend",
                "Old description"
        );

        UpdateCategoryRequest request =
                new UpdateCategoryRequest();

        request.setName("Backend Updated");
        request.setDescription(
                "New description"
        );

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        when(categoryRepository.save(any(Category.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0));

        CategoryResponse result =
                categoryService.update(
                        1L,
                        request
                );

        assertNotNull(result);

        assertEquals(
                "Backend Updated",
                result.getName()
        );

        assertEquals(
                "New description",
                result.getDescription()
        );

        verify(categoryRepository)
                .findById(1L);

        verify(categoryRepository)
                .save(category);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingCategory() {

        UpdateCategoryRequest request =
                new UpdateCategoryRequest();

        request.setName("Backend");
        request.setDescription(
                "Backend technologies"
        );

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> categoryService.update(
                        1L,
                        request
                )
        );

        verify(categoryRepository)
                .findById(1L);

        verify(categoryRepository, never())
                .save(any(Category.class));
    }

    @Test
    void shouldDeleteCategory() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        categoryService.delete(1L);

        verify(categoryRepository)
                .findById(1L);

        verify(categoryRepository)
                .delete(category);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingCategory() {

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> categoryService.delete(1L)
        );

        verify(categoryRepository)
                .findById(1L);

        verify(categoryRepository, never())
                .delete(any(Category.class));
    }
}