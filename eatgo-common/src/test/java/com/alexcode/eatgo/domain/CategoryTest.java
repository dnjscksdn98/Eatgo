package com.alexcode.eatgo.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.alexcode.eatgo.domain.models.Category;
import org.junit.jupiter.api.Test;

class CategoryTest {

  @Test
  public void creation() {
    Category category = Category.builder().name("Fast Food").build();

    assertThat(category.getName(), is("Fast Food"));
  }

}