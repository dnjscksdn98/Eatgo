package com.alexcode.eatgo.domain;

import java.util.List;

import com.alexcode.eatgo.domain.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

  List<Category> findAll();

  Category save(Category category);
}
