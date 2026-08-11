package com.bcodex.techs.controller;

import com.bcodex.techs.dto.request.CreateTechRequest;
import com.bcodex.techs.dto.request.UpdateTechRequest;
import com.bcodex.techs.dto.response.TechResponse;
import com.bcodex.techs.service.TechService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/techs")
public class TechController {


    private final TechService techService;


    public TechController(TechService techService) {
        this.techService = techService;
    }



    @GetMapping
    public ResponseEntity<List<TechResponse>> findAll() {


        return ResponseEntity.ok(
                techService.findAll()
        );

    }



    @GetMapping("/{id}")
    public ResponseEntity<TechResponse> findById(
            @PathVariable Long id) {


        return ResponseEntity.ok(
                techService.findById(id)
        );

    }




    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<TechResponse>> findByCategory(
            @PathVariable Long categoryId) {


        return ResponseEntity.ok(
                techService.findByCategory(categoryId)
        );

    }




    @PostMapping
    public ResponseEntity<TechResponse> create(
            @Valid @RequestBody CreateTechRequest request) {


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(techService.create(request));

    }




    @PutMapping("/{id}")
    public ResponseEntity<TechResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTechRequest request) {


        return ResponseEntity.ok(
                techService.update(id, request)
        );

    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {


        techService.delete(id);

        return ResponseEntity
                .noContent()
                .build();

    }

}