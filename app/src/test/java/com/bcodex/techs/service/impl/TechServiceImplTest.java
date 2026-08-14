package com.bcodex.techs.service.impl;

import com.bcodex.techs.dto.request.CreateTechRequest;
import com.bcodex.techs.dto.request.UpdateTechRequest;
import com.bcodex.techs.dto.response.TechResponse;
import com.bcodex.techs.entity.Category;
import com.bcodex.techs.entity.Tech;
import com.bcodex.techs.exception.ResourceNotFoundException;
import com.bcodex.techs.repository.CategoryRepository;
import com.bcodex.techs.repository.TechRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechServiceImplTest {

    @Mock
    private TechRepository techRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TechServiceImpl techService;

    @Test
    void shouldFindAllTechs() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        Tech tech = new Tech(
                "Spring Boot",
                "Java framework",
                category
        );

        when(techRepository.findAll())
                .thenReturn(List.of(tech));

        List<TechResponse> result =
                techService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(
                "Spring Boot",
                result.get(0).getName()
        );
        assertEquals(
                "Java framework",
                result.get(0).getDescription()
        );
        assertEquals(
                "Backend",
                result.get(0).getCategoryName()
        );

        verify(techRepository).findAll();
    }

    @Test
    void shouldFindTechById() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        Tech tech = new Tech(
                "Spring Boot",
                "Java framework",
                category
        );

        when(techRepository.findById(1L))
                .thenReturn(Optional.of(tech));

        TechResponse result =
                techService.findById(1L);

        assertNotNull(result);
        assertEquals(
                "Spring Boot",
                result.getName()
        );
        assertEquals(
                "Backend",
                result.getCategoryName()
        );

        verify(techRepository)
                .findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenTechNotFound() {

        when(techRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> techService.findById(1L)
        );

        verify(techRepository)
                .findById(1L);
    }

    @Test
    void shouldFindTechsByCategory() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        Tech tech = new Tech(
                "Spring Boot",
                "Java framework",
                category
        );

        when(categoryRepository.existsById(1L))
                .thenReturn(true);

        when(techRepository.findByCategoryId(1L))
                .thenReturn(List.of(tech));

        List<TechResponse> result =
                techService.findByCategory(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(
                "Spring Boot",
                result.get(0).getName()
        );

        verify(categoryRepository)
                .existsById(1L);

        verify(techRepository)
                .findByCategoryId(1L);
    }

    @Test
    void shouldThrowExceptionWhenCategoryDoesNotExist() {

        when(categoryRepository.existsById(99L))
                .thenReturn(false);

        assertThrows(
                ResourceNotFoundException.class,
                () -> techService.findByCategory(99L)
        );

        verify(categoryRepository)
                .existsById(99L);

        verify(techRepository, never())
                .findByCategoryId(anyLong());
    }

    @Test
    void shouldCreateTech() {

        CreateTechRequest request =
                new CreateTechRequest();

        request.setName("Spring Boot");
        request.setDescription("Java framework");
        request.setCategoryId(1L);

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        when(techRepository.existsByName("Spring Boot"))
                .thenReturn(false);

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        Tech savedTech = new Tech(
                "Spring Boot",
                "Java framework",
                category
        );

        when(techRepository.save(any(Tech.class)))
                .thenReturn(savedTech);

        TechResponse result =
                techService.create(request);

        assertNotNull(result);
        assertEquals(
                "Spring Boot",
                result.getName()
        );
        assertEquals(
                "Java framework",
                result.getDescription()
        );
        assertEquals(
                "Backend",
                result.getCategoryName()
        );

        verify(techRepository)
                .existsByName("Spring Boot");

        verify(categoryRepository)
                .findById(1L);

        verify(techRepository)
                .save(any(Tech.class));
    }

    @Test
    void shouldRejectDuplicateTech() {

        CreateTechRequest request =
                new CreateTechRequest();

        request.setName("Spring Boot");
        request.setDescription("Java framework");
        request.setCategoryId(1L);

        when(techRepository.existsByName("Spring Boot"))
                .thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> techService.create(request)
        );

        verify(techRepository)
                .existsByName("Spring Boot");

        verify(categoryRepository, never())
                .findById(anyLong());

        verify(techRepository, never())
                .save(any(Tech.class));
    }

    @Test
    void shouldThrowExceptionWhenCreatingWithInvalidCategory() {

        CreateTechRequest request =
                new CreateTechRequest();

        request.setName("Spring Boot");
        request.setDescription("Java framework");
        request.setCategoryId(99L);

        when(techRepository.existsByName("Spring Boot"))
                .thenReturn(false);

        when(categoryRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> techService.create(request)
        );

        verify(categoryRepository)
                .findById(99L);

        verify(techRepository, never())
                .save(any(Tech.class));
    }

    @Test
    void shouldUpdateTech() {

        Category oldCategory = new Category(
                "Backend",
                "Backend technologies"
        );

        Category newCategory = new Category(
                "Frontend",
                "Frontend technologies"
        );

        Tech tech = new Tech(
                "Spring",
                "Old description",
                oldCategory
        );

        UpdateTechRequest request =
                new UpdateTechRequest();

        request.setName("Spring Boot");
        request.setDescription("New description");
        request.setCategoryId(2L);

        when(techRepository.findById(1L))
                .thenReturn(Optional.of(tech));

        when(categoryRepository.findById(2L))
                .thenReturn(Optional.of(newCategory));

        when(techRepository.save(any(Tech.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0));

        TechResponse result =
                techService.update(1L, request);

        assertNotNull(result);
        assertEquals(
                "Spring Boot",
                result.getName()
        );
        assertEquals(
                "New description",
                result.getDescription()
        );
        assertEquals(
                "Frontend",
                result.getCategoryName()
        );

        verify(techRepository)
                .findById(1L);

        verify(categoryRepository)
                .findById(2L);

        verify(techRepository)
                .save(tech);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingTech() {

        UpdateTechRequest request =
                new UpdateTechRequest();

        request.setName("Spring Boot");
        request.setDescription("Java framework");
        request.setCategoryId(1L);

        when(techRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> techService.update(1L, request)
        );

        verify(techRepository)
                .findById(1L);

        verify(categoryRepository, never())
                .findById(anyLong());

        verify(techRepository, never())
                .save(any(Tech.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingWithInvalidCategory() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        Tech tech = new Tech(
                "Spring",
                "Java framework",
                category
        );

        UpdateTechRequest request =
                new UpdateTechRequest();

        request.setName("Spring Boot");
        request.setDescription("Java framework");
        request.setCategoryId(99L);

        when(techRepository.findById(1L))
                .thenReturn(Optional.of(tech));

        when(categoryRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> techService.update(1L, request)
        );

        verify(categoryRepository)
                .findById(99L);

        verify(techRepository, never())
                .save(any(Tech.class));
    }

    @Test
    void shouldDeleteTech() {

        Category category = new Category(
                "Backend",
                "Backend technologies"
        );

        Tech tech = new Tech(
                "Spring Boot",
                "Java framework",
                category
        );

        when(techRepository.findById(1L))
                .thenReturn(Optional.of(tech));

        techService.delete(1L);

        verify(techRepository)
                .findById(1L);

        verify(techRepository)
                .delete(tech);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingTech() {

        when(techRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> techService.delete(99L)
        );

        verify(techRepository)
                .findById(99L);

        verify(techRepository, never())
                .delete(any(Tech.class));
    }
}