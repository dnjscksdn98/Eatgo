package com.alexcode.eatgo.interfaces;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexcode.eatgo.application.ReviewService;
import com.alexcode.eatgo.domain.models.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class ReviewControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ReviewService reviewService;

  @Test
  public void createWithValidData() throws Exception {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJ0ZXN0ZXIifQ.lFpZkkerjEjRKo51nzjgMhTxJeV0aEFgPul9G8xQvU8";

    given(reviewService.addReview(1L, "tester", 3.5, "yummy")).willReturn(
        Review.builder()
            .id(1L)
            .createdBy("tester")
            .score(3.5)
            .content("yummy")
            .build()
    );

    mvc.perform(post("/restaurants/1/reviews")
        .header("Authorization", "Bearer " + token)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"score\": 3.5, \"content\": \"yummy\"}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "/restaurants/1/reviews/1"));

    verify(reviewService).addReview(eq(1L), eq("tester"), eq(3.5), eq("yummy"));
  }

  @Test
  public void createWithInvalidData() throws Exception {
    mvc.perform(post("/restaurants/1/reviews")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{}"))
        .andExpect(status().isBadRequest());

    verify(reviewService, never()).addReview(any(), any(), any(), any());
  }
}