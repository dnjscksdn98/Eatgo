package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.CategoryService;
import com.alexcode.eatgo.interfaces.dto.CategoryRequestDto;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "management/api/v1/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('category:read')")
  public ResponseEntity<SuccessResponse<?>> list() {
    SuccessResponse<?> body = categoryService.list();
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('category:write')")
  public ResponseEntity<SuccessResponse<?>> create(@Valid @RequestBody CategoryRequestDto request) {
    SuccessResponse<?> body = categoryService.create(request);
    return new ResponseEntity<>(body, HttpStatus.CREATED);
  }

  @PatchMapping(path = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('category:write')")
  public ResponseEntity<SuccessResponse<?>> update(
          @PathVariable("categoryId") Long categoryId,
          @Valid @RequestBody CategoryRequestDto request) {

    SuccessResponse<?> body = categoryService.update(request, categoryId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @DeleteMapping(path = "/{categoryId}")
  @PreAuthorize("hasAuthority('category:write')")
  public ResponseEntity<SuccessResponse<?>> delete(@PathVariable("categoryId") Long categoryId) {
    SuccessResponse<?> body = categoryService.delete(categoryId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

}
