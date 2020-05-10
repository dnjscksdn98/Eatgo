package com.alexcode.eatgo.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  public void creation() {
    User user = User.builder()
        .email("tester@example.com")
        .name("tester")
        .level(3L)
        .build();

    assertThat(user.getName(), is("tester"));
    assertThat(user.isAdmin(), is(true));

    user.deactivate();

    assertThat(user.isActive(), is(false));
  }

  @Test
  public void accessToken() {
    User user = User.builder().password("ACCESSTOKEN").build();

    assertThat(user.getAccessToken(), is("ACCESSTOKE"));
  }
}