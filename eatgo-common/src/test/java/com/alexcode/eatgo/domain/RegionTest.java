package com.alexcode.eatgo.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.alexcode.eatgo.domain.models.Region;
import org.junit.jupiter.api.Test;

class RegionTest {

  @Test
  public void creation() {
    Region region = Region.builder().name("서울").build();

    assertThat(region.getName(), is("서울"));
  }
}