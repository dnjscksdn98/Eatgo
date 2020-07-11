package com.alexcode.eatgo.interfaces;

//import com.alexcode.eatgo.application.UserService;
//import com.alexcode.eatgo.application.exceptions.EmailNotExistsException;
//import com.alexcode.eatgo.application.exceptions.WrongPasswordException;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(SessionController.class)
class SessionControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private JwtUtil jwtUtil;

//  @MockBean
//  private UserService userService;

  @Test
  public void loginWithValidData() throws Exception {
    Long id = 1004L;
    String name = "tester";
    String email = "tester@example.com";
    String password = "tester";
    Long level = 1L;

    User mockUser = User.builder()
            .id(id)
            .name(name)
            .level(level)
            .build();

//    given(userService.authenticate(email, password)).willReturn(mockUser);
    given(jwtUtil.createToken(id, name, null)).willReturn("header.payload.signature");

    mvc.perform(post("/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\": \"tester@example.com\", \"password\": \"tester\"}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "/session"))
        .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}")));

//    verify(userService).authenticate(eq(email), eq(password));
  }

  @Test
  public void loginToRestaurantOwner() throws Exception {
    Long id = 1004L;
    String name = "tester";
    String email = "tester@example.com";
    String password = "tester";
    Long restaurantId = 1L;
    Long level = 50L;

//    User mockUser = User.builder()
//            .id(id)
//            .name(name)
//            .email(email)
//            .level(level)
//            .restaurantId(restaurantId)
//            .build();
//
//    given(userService.authenticate(email, password)).willReturn(mockUser);
    given(jwtUtil.createToken(id, name, restaurantId)).willReturn("header.payload.signature");

    mvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\": \"tester@example.com\", \"password\": \"tester\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("location", "/session"))
            .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}")));

//    verify(userService).authenticate(eq(email), eq(password));
    verify(jwtUtil).createToken(eq(id), eq(name), eq(restaurantId));
  }

  @Test
  public void loginWithWrongPassword() throws Exception {
//    given(userService.authenticate("tester@example.com", "wrongpw"))
//        .willThrow(new WrongPasswordException());
//
//    mvc.perform(post("/login")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content("{\"email\": \"tester@example.com\", \"password\": \"wrongpw\"}"))
//        .andExpect(status().isBadRequest());
//
//    verify(userService).authenticate(eq("tester@example.com"), eq("wrongpw"));
  }

  @Test
  public void loginWithNonExistedEmail() throws Exception {
//    given(userService.authenticate("nonexist@example.com", "testerpw"))
//        .willThrow(new EmailNotExistsException());
//
//    mvc.perform(post("/login")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content("{\"email\": \"nonexist@example.com\", \"password\": \"testerpw\"}"))
//        .andExpect(status().isBadRequest());
//
//    verify(userService).authenticate(eq("nonexist@example.com"), eq("testerpw"));
  }

  @Test
  public void loginWithInvalidEmailForm() throws Exception {
    mvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"test\", \"password\": \"test\"}"))
            .andExpect(status().isBadRequest());

//    verify(userService, never()).authenticate(any(), any());
  }

  @Test
  public void loginWithEmptyData() throws Exception {
    mvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"))
            .andExpect(status().isBadRequest());

//    verify(userService, never()).authenticate(any(), any());
  }
}