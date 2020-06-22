package com.alexcode.eatgo.application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import com.alexcode.eatgo.domain.models.Category;
import com.alexcode.eatgo.domain.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryServiceTest {

  private CategoryService categoryService;

  @Mock
  private CategoryRepository categoryRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    categoryService = new CategoryService(categoryRepository);
  }

  @Test
  public void getCategories() {
    List<Category> mockCategories = new ArrayList<>();
    mockCategories.add(Category.builder().name("Fast Food").build());

    given(categoryRepository.findAll()).willReturn(mockCategories);

    List<Category> categories = categoryService.getCategories();

    Category category = categories.get(0);
    assertThat(category.getName(), is("Fast Food"));
  }

}