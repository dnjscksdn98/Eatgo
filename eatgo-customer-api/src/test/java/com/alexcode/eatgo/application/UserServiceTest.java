package com.alexcode.eatgo.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.alexcode.eatgo.application.exceptions.EmailExistsException;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest {

  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    userService = new UserService(userRepository, passwordEncoder);
  }

  @Test
  public void registerUser() {
    String email = "tester@example.com";
    String name = "tester";
    String password = "testerpw";

    userService.registerUser(email, name, password);

    verify(userRepository).save(any());
  }

  @Test
  public void registerUserWithExistedEmail() {
    String email = "tester@example.com";
    String name = "tester";
    String password = "testerpw";

    User user = User.builder().build();

    Assertions.assertThrows(EmailExistsException.class, () -> {
      given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

      userService.registerUser(email, name, password);

      verify(userRepository, never()).save(any());
    });
  }

}