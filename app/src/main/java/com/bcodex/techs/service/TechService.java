package com.bcodex.techs.service;

import com.bcodex.techs.dto.request.CreateTechRequest;
import com.bcodex.techs.dto.request.UpdateTechRequest;
import com.bcodex.techs.dto.response.TechResponse;

import java.util.List;

public interface TechService {

    List<TechResponse> findAll();

    TechResponse findById(Long id);

    List<TechResponse> findByCategory(Long categoryId);

    TechResponse create(CreateTechRequest request);

    TechResponse update(Long id, UpdateTechRequest request);

    void delete(Long id);

}