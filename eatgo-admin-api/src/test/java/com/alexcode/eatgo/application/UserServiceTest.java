package com.alexcode.eatgo.application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.alexcode.eatgo.domain.User;
import com.alexcode.eatgo.domain.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    userService = new UserService(userRepository);
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

    User user = userService.addUser(email, name);

    assertThat(user.getName(), is(name));
  }

}