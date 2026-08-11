package com.bcodex.techs.mapper;

import com.bcodex.techs.dto.response.CategoryResponse;
import com.bcodex.techs.entity.Category;

public final class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryResponse toResponse(Category entity) {

        if (entity == null) {
            return null;
        }

        CategoryResponse response = new CategoryResponse();

        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        return response;

    }

}