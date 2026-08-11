package com.bcodex.techs.mapper;

import com.bcodex.techs.dto.response.TechResponse;
import com.bcodex.techs.entity.Tech;

public final class TechMapper {

    private TechMapper() {
    }

    public static TechResponse toResponse(Tech entity) {

        if (entity == null) {
            return null;
        }

        TechResponse response = new TechResponse();

        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());

        response.setCategoryId(entity.getCategory().getId());
        response.setCategoryName(entity.getCategory().getName());

        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        return response;

    }

}