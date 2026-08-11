package com.bcodex.techs.service.impl;

import com.bcodex.techs.dto.request.CreateCategoryRequest;
import com.bcodex.techs.dto.request.UpdateCategoryRequest;
import com.bcodex.techs.dto.response.CategoryResponse;
import com.bcodex.techs.entity.Category;
import com.bcodex.techs.exception.ResourceNotFoundException;
import com.bcodex.techs.mapper.CategoryMapper;
import com.bcodex.techs.repository.CategoryRepository;
import com.bcodex.techs.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {

        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found: " + id));

        return CategoryMapper.toResponse(category);

    }

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {

        if (categoryRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException(
                    "Category already exists.");
        }

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category saved = categoryRepository.save(category);

        return CategoryMapper.toResponse(saved);

    }

    @Override
    public CategoryResponse update(Long id,
                                   UpdateCategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found: " + id));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updated = categoryRepository.save(category);

        return CategoryMapper.toResponse(updated);

    }

    @Override
    public void delete(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found: " + id));

        categoryRepository.delete(category);

    }

}