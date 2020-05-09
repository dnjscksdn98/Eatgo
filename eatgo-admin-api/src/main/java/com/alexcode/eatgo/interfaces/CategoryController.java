package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.CategoryService;
import com.alexcode.eatgo.domain.Category;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping("/categories")
  public List<Category> list() {
    return categoryService.getCategories();
  }

  @PostMapping("/categories")
  public ResponseEntity<?> create(@RequestBody Category resource) throws URISyntaxException {
    Category category = categoryService.addCategory(resource.getName());
    URI location = new URI("/categories/" + category.getId());
    return ResponseEntity.created(location).body("{}");
  }
}
