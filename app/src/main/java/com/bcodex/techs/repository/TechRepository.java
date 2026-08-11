package com.bcodex.techs.repository;

import com.bcodex.techs.entity.Category;
import com.bcodex.techs.entity.Tech;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TechRepository extends JpaRepository<Tech, Long> {

    Optional<Tech> findByName(String name);

    boolean existsByName(String name);

    List<Tech> findByCategory(Category category);

    List<Tech> findByCategoryId(Long categoryId);

}