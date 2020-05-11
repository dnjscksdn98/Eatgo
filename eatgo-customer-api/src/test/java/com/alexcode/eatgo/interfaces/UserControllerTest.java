package com.alexcode.eatgo.interfaces;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.User;
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
  public void register() throws Exception {
    String email = "tester@example.com";
    String name = "tester";
    String password = "testerpw";

    User mockUser = User.builder()
        .id(1L)
        .email(email)
        .name(name)
        .password(password)
        .build();

    given(userService.registerUser(email, name, password)).willReturn(mockUser);

    mvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\": \"tester@example.com\", \"name\": \"tester\", \"password\": \"testerpw\"}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "/users/1"));

    verify(userService).registerUser(eq(email), eq(name), eq(password));
  }

}