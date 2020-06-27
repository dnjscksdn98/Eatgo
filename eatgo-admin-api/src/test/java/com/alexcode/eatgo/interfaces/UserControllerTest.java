package com.alexcode.eatgo.interfaces;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.models.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @Test
  public void list() throws Exception {
    List<User> users = new ArrayList<>();
    users.add(User.builder()
        .email("tester@example.com")
        .name("tester")
        .level(1L)
        .build()
    );

    given(userService.getUsers()).willReturn(users);

    mvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("tester")));
  }

  @Test
  public void create() throws Exception {
    String email = "tester@example.com";
    String name = "tester";

    User user = User.builder()
        .email(email)
        .name(name)
        .build();

    given(userService.addUser(email, name)).willReturn(user);

    mvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\": \"tester@example.com\", \"name\": \"tester\"}"))
        .andExpect(status().isCreated());

    verify(userService).addUser(email, name);
  }

  @Test
  public void createWithInvalidData() throws Exception {
    mvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"tester\", \"name\":\"tester\"}"))
            .andExpect(status().isBadRequest());

    verify(userService, never()).addUser(any(), any());
  }

  @Test
  public void update() throws Exception {
    mvc.perform(patch("/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\": \"tester@example.com\", \"name\": \"tester\", \"level\": 3}"))
        .andExpect(status().isOk());

    Long id = 1L;
    String email = "tester@example.com";
    String name = "tester";
    Long level = 3L;

    verify(userService).updateUser(eq(id), eq(email), eq(name), eq(level));
  }

  @Test
  public void deactivate() throws Exception {
    mvc.perform(delete("/users/1"))
        .andExpect(status().isOk());

    verify(userService).deactivateUser(1L);
  }
}