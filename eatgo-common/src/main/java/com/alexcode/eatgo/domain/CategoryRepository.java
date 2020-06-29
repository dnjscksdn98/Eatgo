package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.domain.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

  List<Category> findAll();

  Optional<Category> findByName(String name);

  Category save(Category category);
}
