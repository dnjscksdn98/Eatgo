package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.CategoryService;
import com.alexcode.eatgo.domain.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customer/api/v1/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  @PreAuthorize("hasAuthority('category:read')")
  public List<Category> list() {
    return categoryService.getCategories();
  }

}
