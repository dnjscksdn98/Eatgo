package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.domain.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  public void creation() {
    User user = User.builder()
        .email("tester@example.com")
        .name("tester")
        .level(100L)
        .build();

    assertEquals(user.getName(), "tester");
    assertTrue(user.isAdmin());

    user.deactivate();
    assertFalse(user.isActive());
  }

  @Test
  public void createRestaurantOwner() {
    User user = User.builder().level(1L).build();
    assertFalse(user.isRestaurantOwner());

    user.setRestaurantId(1004L);
    assertTrue(user.isRestaurantOwner());

    assertEquals(user.getRestaurantId(), 1004L);
  }

}