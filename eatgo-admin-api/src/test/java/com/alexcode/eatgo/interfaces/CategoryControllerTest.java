package com.alexcode.eatgo.interfaces;


import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexcode.eatgo.application.CategoryService;
import com.alexcode.eatgo.domain.models.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alexcode.eatgo.network.SuccessResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CategoryService categoryService;

  @Test
  public void list() throws Exception {
    List<Category> categories = new ArrayList<>();
    Category category = Category.builder()
            .name("분식")
            .createdAt(LocalDateTime.now())
            .createdBy("ADMIN")
            .build();
    categories.add(category);
    SuccessResponse response = SuccessResponse.OK(categories, 200);

    given(categoryService.list()).willReturn(response);

    mvc.perform(get("/categories"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("분식")));
  }

  @Test
  public void create() throws Exception {
    Category category = Category.builder().name("Fast Food").build();
//    given(categoryService.addCategory("Fast Food")).willReturn(category);

    mvc.perform(post("/categories")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"Fast Food\"}"))
        .andExpect(status().isCreated())
        .andExpect(content().string("{}"));

//    verify(categoryService).addCategory("Fast Food");
  }

  @Test
  public void createWithInvalidData() throws Exception {
    mvc.perform(post("/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"\"}"))
            .andExpect(status().isBadRequest());

//    verify(categoryService, never()).addCategory(any());
  }

  @Test
  public void createWithExistedData() throws Exception {
//    given(categoryService.addCategory("Seoul"))
//            .willThrow(new CategoryDuplicationException("Seoul"));

    mvc.perform(post("/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Seoul\"}"))
            .andExpect(status().isBadRequest());

//    verify(categoryService).addCategory(eq("Seoul"));
  }
}