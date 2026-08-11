package com.bcodex.techs.controller;

import com.bcodex.techs.dto.request.CreateCategoryRequest;
import com.bcodex.techs.dto.request.UpdateCategoryRequest;
import com.bcodex.techs.dto.response.CategoryResponse;
import com.bcodex.techs.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void findAll_shouldReturnOk() {

        List<CategoryResponse> response = List.of(
                new CategoryResponse()
        );

        when(categoryService.findAll()).thenReturn(response);

        ResponseEntity<List<CategoryResponse>> result =
                categoryController.findAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(categoryService).findAll();
    }

    @Test
    void findById_shouldReturnOk() {

        Long id = 1L;
        CategoryResponse response = new CategoryResponse();

        when(categoryService.findById(id)).thenReturn(response);

        ResponseEntity<CategoryResponse> result =
                categoryController.findById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(categoryService).findById(id);
    }

    @Test
    void create_shouldReturnCreated() {

        CreateCategoryRequest request = new CreateCategoryRequest();
        CategoryResponse response = new CategoryResponse();

        when(categoryService.create(request)).thenReturn(response);

        ResponseEntity<CategoryResponse> result =
                categoryController.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(categoryService).create(request);
    }

    @Test
    void update_shouldReturnOk() {

        Long id = 1L;
        UpdateCategoryRequest request = new UpdateCategoryRequest();
        CategoryResponse response = new CategoryResponse();

        when(categoryService.update(id, request)).thenReturn(response);

        ResponseEntity<CategoryResponse> result =
                categoryController.update(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(categoryService).update(id, request);
    }

    @Test
    void delete_shouldReturnNoContent() {

        Long id = 1L;

        doNothing().when(categoryService).delete(id);

        ResponseEntity<Void> result =
                categoryController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertNull(result.getBody());

        verify(categoryService).delete(id);
    }
}