package com.alexcode.eatgo.application;

//import com.alexcode.eatgo.exceptions.EmailNotExistsException;
//import com.alexcode.eatgo.exceptions.WrongPasswordException;
import com.alexcode.eatgo.domain.UserRepository;
import com.alexcode.eatgo.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


class UserServiceTest {

//  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
//    userService = new UserService(userRepository, passwordEncoder);
  }

  @Test
  public void authenticateWithValidData() {
    String email = "tester@example.com";
    String password = "testerpw";
    User mockUser = User.builder().email(email).build();

    given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
    given(passwordEncoder.matches(any(), any())).willReturn(true);

//    User user = userService.authenticate(email, password);

//    assertEquals(user.getEmail(), email);
  }

  @Test
  public void authenticateWithNotExistedEmail() {
    String email = "wrong@example.com";
    String password = "testerpw";

    given(userRepository.findByEmail(email)).willReturn(Optional.empty());

//    assertThrows(EmailNotExistsException.class, () -> {
//      userService.authenticate(email, password);
//    });
  }

  @Test
  public void authenticateWithWrongPassword() {
    String email = "tester@example.com";
    String password = "wrongpw";
    User mockUser = User.builder().email(email).build();

    given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
    given(passwordEncoder.matches(any(), any())).willReturn(false);

//    assertThrows(WrongPasswordException.class, () -> {
//      userService.authenticate(email, password);
//    });
  }

}