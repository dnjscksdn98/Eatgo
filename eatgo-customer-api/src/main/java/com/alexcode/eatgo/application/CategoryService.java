package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.Category;
import com.alexcode.eatgo.domain.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }

}
