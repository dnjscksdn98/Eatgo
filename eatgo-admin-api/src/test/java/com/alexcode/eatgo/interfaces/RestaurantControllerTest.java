package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.models.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RestaurantService restaurantService;

  @Test
  public void list() throws Exception {
    List<Restaurant> restaurants = new ArrayList<>();
//    restaurants.add(Restaurant.builder()
//            .name("TestName")
//            .address("TestAddress")
//            .categoryId(1L)
//            .build()
//    );
//    given(restaurantService.getRestaurants()).willReturn(restaurants);

    mvc.perform(get("/restaurants"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"name\":\"TestName\"")));
  }

  @Test
  public void detailWithExistedData() throws Exception {
//    Restaurant restaurant = Restaurant.builder()
//            .name("TestName")
//            .address("TestAddress")
//            .categoryId(1L)
//            .build();
//
//    given(restaurantService.getRestaurantById(1004L)).willReturn(restaurant);

    mvc.perform(get("/restaurants/1004"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"name\":\"TestName\"")));
  }

  @Test
  public void detailWithNonExistedData() throws Exception {
//    given(restaurantService.getRestaurantById(999L))
//            .willThrow(new RestaurantNotFoundException(999L));

    mvc.perform(get("/restaurants/999"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void createWithValidData() throws Exception {
//    given(restaurantService.addRestaurant(any(), any(), any()))
//            .willReturn(
//                    Restaurant.builder()
//                            .name("TestName")
//                            .address("TestAddress")
//                            .categoryId(1L)
//                            .build()
//            );


    mvc.perform(post("/restaurants")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"TestName\", \"address\": \"TestAddress\", \"categoryId\": 1}"))
        .andExpect(status().isCreated())
        .andExpect(content().string("{}"));

//    verify(restaurantService).addRestaurant(any(), any(), any());
  }

  @Test
  public void createWithInvalidData() throws Exception {
    mvc.perform(post("/restaurants")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"\", \"address\": \"\", \"categoryId\": 1}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void updateWithValidData() throws Exception {
    mvc.perform(patch("/restaurants/1004")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"TestName\", \"address\": \"TestAddress\"}"))
        .andExpect(status().isOk());

//    verify(restaurantService).updateRestaurant(any(), any(), any());
  }

  @Test
  public void updateWithInvalidData() throws Exception {
    mvc.perform(patch("/restaurants/1004")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"\", \"address\": \"\"}"))
        .andExpect(status().isBadRequest());
  }
}