package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static com.alexcode.eatgo.security.ApplicationUserRole.CUSTOMER;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void creation() {
    User user = User.builder()
            .email("tester01@test.com")
            .name("tester01")
            .password("tester01")
            .level(1L)
            .role(CUSTOMER)
            .createdAt(LocalDateTime.now())
            .createdBy("AdminServer")
            .build();

    User savedUser = userRepository.save(user);

    assertNotNull(savedUser);
    assertEquals(savedUser.getEmail(), "tester01@test.com");
  }

  @Test
  public void createRestaurantOwner() {
    User user = User.builder().level(1L).build();
    assertFalse(user.isRestaurantOwner());

//    user.setRestaurantId(1004L);
//    assertTrue(user.isRestaurantOwner());
//
//    assertEquals(user.getRestaurantId(), 1004L);
  }

}