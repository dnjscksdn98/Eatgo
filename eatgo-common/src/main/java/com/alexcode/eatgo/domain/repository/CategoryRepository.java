package com.alexcode.eatgo.domain.repository;

import com.alexcode.eatgo.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  List<Category> findAll();

  Optional<Category> findByName(String name);

  Category save(Category category);
}
