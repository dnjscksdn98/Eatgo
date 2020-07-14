package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.CategoryRepository;
import com.alexcode.eatgo.domain.models.Category;
import com.alexcode.eatgo.domain.network.SuccessCode;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.CategoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public SuccessResponse<List<CategoryResponseDto>> getCategories() {
    List<Category> categories = categoryRepository.findAll();

    return response(categories, HttpStatus.OK.value(), SuccessCode.OK);
  }

  private SuccessResponse<List<CategoryResponseDto>> response(
          List<Category> categories, Integer status, SuccessCode successCode) {

    List<CategoryResponseDto> data = categories.stream()
            .map(category -> CategoryResponseDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build())
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status, successCode);
  }

}
