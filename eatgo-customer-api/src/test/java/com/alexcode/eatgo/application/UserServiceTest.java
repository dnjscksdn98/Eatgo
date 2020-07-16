package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.repository.UserRepository;
import com.alexcode.eatgo.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
    String confirmPassword = "testerpw";

//    userService.registerUser(email, name, password, confirmPassword);

    verify(userRepository).save(any());
  }

  @Test
  public void registerUserWithExistedEmail() {
    String email = "tester@example.com";
    String name = "tester";
    String password = "testerpw";
    String confirmPassword = "testerpw";

    User user = User.builder().build();

//    assertThrows(EmailDuplicationException.class, () -> {
//      given(userRepository.findByEmail(email)).willReturn(Optional.of(user));
//
//      userService.registerUser(email, name, password, confirmPassword);
//
//      verify(userRepository, never()).save(any());
//    });
  }

  @Test
  public void registerUserWithPasswordMismatch() {
    String email = "tester@example.com";
    String name = "tester";
    String password = "testerpw";
    String confirmPassword = "mismatch";

//    assertThrows(PasswordMismatchException.class, () -> {
//      given(userRepository.findByEmail(email))
//              .willReturn(Optional.empty());
//
//      userService.registerUser(email, name, password, confirmPassword);
//
//      verify(userRepository, never()).save(any());
//    });
  }

}