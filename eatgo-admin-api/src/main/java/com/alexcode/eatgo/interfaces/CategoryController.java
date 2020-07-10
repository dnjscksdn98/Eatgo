package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.CategoryService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.CategoryRequestDto;
import com.alexcode.eatgo.interfaces.dto.CategoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "management/api/v1/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  @PreAuthorize("hasAuthority('category:read')")
  public SuccessResponse<List<CategoryResponseDto>> list() {
    return categoryService.list();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('category:write')")
  public SuccessResponse<CategoryResponseDto> create(@Valid @RequestBody CategoryRequestDto request) {
    return categoryService.create(request);
  }

  @PatchMapping(path = "/{categoryId}")
  @PreAuthorize("hasAuthority('category:write')")
  public SuccessResponse<CategoryResponseDto> update(
          @PathVariable("categoryId") Long categoryId,
          @Valid @RequestBody CategoryRequestDto request) {

    return categoryService.update(request, categoryId);
  }

  @DeleteMapping(path = "/{categoryId}")
  @PreAuthorize("hasAuthority('category:write')")
  public SuccessResponse delete(@PathVariable("categoryId") Long categoryId) {
    return categoryService.delete(categoryId);
  }

}
