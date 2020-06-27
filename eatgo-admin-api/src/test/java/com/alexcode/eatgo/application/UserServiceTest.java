package com.alexcode.eatgo.application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alexcode.eatgo.application.exceptions.EmailDuplicationException;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
  public void getUsers() {
    List<User> mockUsers = new ArrayList<>();
    mockUsers.add(User.builder()
        .email("tester@example.com")
        .name("tester")
        .build()
    );
    given(userRepository.findAll()).willReturn(mockUsers);

    List<User> users = userService.getUsers();

    assertThat(users.get(0).getName(), is("tester"));
  }

  @Test
  public void addUser() {
    String email = "tester@example.com";
    String name = "tester";
    User mockUser = User.builder()
        .email(email)
        .name(name)
        .build();

    given(userRepository.save(any())).willReturn(mockUser);
    given(userRepository.findByEmail(email)).willReturn(Optional.empty());

    User user = userService.addUser(email, name);

    assertEquals(user.getName(), name);
    assertEquals(user.getEmail(), email);
  }

  @Test
  public void addUserWithEmailDuplication() {
    String email = "tester@example.com";
    String name = "tester";
    given(userRepository.findByEmail(any()))
            .willReturn(Optional.of(
                    User.builder()
                      .email(email)
                      .name(name)
                      .build())
            );

    assertThrows(EmailDuplicationException.class, () -> {
      userService.addUser(email, name);
    });
  }

  @Test
  public void updateUser() {
    Long id = 1L;
    String email = "tester@example.com";
    String name = "tester2";
    Long level = 100L;

    User mockUser = User.builder()
        .id(id)
        .email(email)
        .name("tester")
        .level(1L)
        .build();

    given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

    User user = userService.updateUser(id, email, name, level);

    verify(userRepository).findById(eq(id));

    assertThat(user.getName(), is("tester2"));
    assertThat(user.isAdmin(), is(true));
  }

  @Test
  public void deactivateUser() {
    Long id = 1L;
    String email = "tester@example.com";
    String name = "tester";
    Long level = 3L;

    User mockUser = User.builder()
        .id(id)
        .email(email)
        .name(name)
        .level(level)
        .build();

    given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

    User user = userService.deactivateUser(1L);

    verify(userRepository).findById(eq(id));

    assertThat(user.isActive(), is(false));
  }

}