package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.CategoryService;
import com.alexcode.eatgo.domain.models.Category;
import com.alexcode.eatgo.interfaces.dto.CategoryCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("admin/api/v1/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  @PreAuthorize("hasAuthority('category:read')")
  public List<Category> list() {
    return categoryService.getCategories();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('category:write')")
  public ResponseEntity<?> create(
          @Valid @RequestBody CategoryCreateDto resource) throws URISyntaxException {

    Category category = categoryService.addCategory(resource.getName());
    URI location = new URI("/categories/" + category.getId());

    return ResponseEntity.created(location).body("{}");
  }
}
