package com.bcodex.techs.service.impl;

import com.bcodex.techs.dto.request.CreateTechRequest;
import com.bcodex.techs.dto.request.UpdateTechRequest;
import com.bcodex.techs.dto.response.TechResponse;
import com.bcodex.techs.entity.Category;
import com.bcodex.techs.entity.Tech;
import com.bcodex.techs.exception.ResourceNotFoundException;
import com.bcodex.techs.mapper.TechMapper;
import com.bcodex.techs.repository.CategoryRepository;
import com.bcodex.techs.repository.TechRepository;
import com.bcodex.techs.service.TechService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TechServiceImpl implements TechService {

    private final TechRepository techRepository;
    private final CategoryRepository categoryRepository;


    public TechServiceImpl(
            TechRepository techRepository,
            CategoryRepository categoryRepository) {

        this.techRepository = techRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<TechResponse> findAll() {

        return techRepository.findAll()
                .stream()
                .map(TechMapper::toResponse)
                .toList();

    }


    @Override
    @Transactional(readOnly = true)
    public TechResponse findById(Long id) {

        Tech tech = techRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Technology not found: " + id));

        return TechMapper.toResponse(tech);

    }


    @Override
    @Transactional(readOnly = true)
    public List<TechResponse> findByCategory(Long categoryId) {


        if (!categoryRepository.existsById(categoryId)) {

            throw new ResourceNotFoundException(
                    "Category not found: " + categoryId);

        }


        return techRepository.findByCategoryId(categoryId)
                .stream()
                .map(TechMapper::toResponse)
                .toList();

    }



    @Override
    public TechResponse create(CreateTechRequest request) {


        if (techRepository.existsByName(request.getName())) {

            throw new IllegalArgumentException(
                    "Technology already exists.");

        }


        Category category = categoryRepository.findById(
                        request.getCategoryId())

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found: "
                                        + request.getCategoryId()));



        Tech tech = new Tech();

        tech.setName(request.getName());
        tech.setDescription(request.getDescription());
        tech.setCategory(category);



        Tech saved = techRepository.save(tech);


        return TechMapper.toResponse(saved);

    }



    @Override
    public TechResponse update(Long id,
                               UpdateTechRequest request) {


        Tech tech = techRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Technology not found: " + id));



        Category category = categoryRepository.findById(
                        request.getCategoryId())

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found: "
                                        + request.getCategoryId()));



        tech.setName(request.getName());
        tech.setDescription(request.getDescription());
        tech.setCategory(category);



        Tech updated = techRepository.save(tech);



        return TechMapper.toResponse(updated);

    }



    @Override
    public void delete(Long id) {


        Tech tech = techRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Technology not found: " + id));



        techRepository.delete(tech);

    }

}