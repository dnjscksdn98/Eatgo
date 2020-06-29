package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.CategoryDuplicationException;
import com.alexcode.eatgo.domain.CategoryRepository;
import com.alexcode.eatgo.domain.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public Category addCategory(String name){
    if(categoryRepository.findByName(name).isPresent()) {
      throw new CategoryDuplicationException(name);
    }
    Category category = Category.builder().name(name).build();
    categoryRepository.save(category);
    return category;
  }
}
