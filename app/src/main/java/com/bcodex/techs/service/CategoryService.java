package com.bcodex.techs.service;

import com.bcodex.techs.dto.request.CreateCategoryRequest;
import com.bcodex.techs.dto.request.UpdateCategoryRequest;
import com.bcodex.techs.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();

    CategoryResponse findById(Long id);

    CategoryResponse create(CreateCategoryRequest request);

    CategoryResponse update(Long id, UpdateCategoryRequest request);

    void delete(Long id);

}