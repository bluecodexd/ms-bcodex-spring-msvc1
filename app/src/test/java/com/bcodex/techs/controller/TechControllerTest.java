package com.bcodex.techs.controller;

import com.bcodex.techs.dto.request.CreateTechRequest;
import com.bcodex.techs.dto.request.UpdateTechRequest;
import com.bcodex.techs.dto.response.TechResponse;
import com.bcodex.techs.service.TechService;
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
class TechControllerTest {

    @Mock
    private TechService techService;

    @InjectMocks
    private TechController techController;

    @Test
    void findAll_shouldReturnOk() {

        List<TechResponse> response = List.of(
                new TechResponse()
        );

        when(techService.findAll()).thenReturn(response);

        ResponseEntity<List<TechResponse>> result =
                techController.findAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(techService).findAll();
    }

    @Test
    void findById_shouldReturnOk() {

        Long id = 1L;
        TechResponse response = new TechResponse();

        when(techService.findById(id)).thenReturn(response);

        ResponseEntity<TechResponse> result =
                techController.findById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(techService).findById(id);
    }

    @Test
    void findByCategory_shouldReturnOk() {

        Long categoryId = 1L;

        List<TechResponse> response = List.of(
                new TechResponse()
        );

        when(techService.findByCategory(categoryId))
                .thenReturn(response);

        ResponseEntity<List<TechResponse>> result =
                techController.findByCategory(categoryId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(techService).findByCategory(categoryId);
    }

    @Test
    void create_shouldReturnCreated() {

        CreateTechRequest request = new CreateTechRequest();
        TechResponse response = new TechResponse();

        when(techService.create(request)).thenReturn(response);

        ResponseEntity<TechResponse> result =
                techController.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(techService).create(request);
    }

    @Test
    void update_shouldReturnOk() {

        Long id = 1L;
        UpdateTechRequest request = new UpdateTechRequest();
        TechResponse response = new TechResponse();

        when(techService.update(id, request)).thenReturn(response);

        ResponseEntity<TechResponse> result =
                techController.update(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(techService).update(id, request);
    }

    @Test
    void delete_shouldReturnNoContent() {

        Long id = 1L;

        doNothing().when(techService).delete(id);

        ResponseEntity<Void> result =
                techController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertNull(result.getBody());

        verify(techService).delete(id);
    }
}