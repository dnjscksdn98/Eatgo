package com.alexcode.eatgo.interfaces;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.MenuItem;
import com.alexcode.eatgo.domain.Restaurant;
import com.alexcode.eatgo.domain.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.Review;
import com.alexcode.eatgo.interfaces.RestaurantController;
import java.util.ArrayList;
import java.util.Arrays;
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
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RestaurantService restaurantService;

  @Test
  public void list() throws Exception {
    List<Restaurant> restaurants = new ArrayList<>();
    restaurants.add(Restaurant.builder()
        .id(1004L)
        .name("Bob zip")
        .address("Seoul")
        .build()
    );
    given(restaurantService.getRestaurants()).willReturn(restaurants);

    mvc.perform(get("/restaurants"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
        .andExpect(content().string(containsString("\"id\":1004")));
  }

  @Test
  public void detailWithExistedData() throws Exception {
    Restaurant restaurant = Restaurant.builder()
        .id(1004L)
        .name("Bob zip")
        .address("Seoul")
        .build();

    MenuItem menuItem = MenuItem.builder()
        .name("Kimchi")
        .build();

    Review review = Review.builder()
        .name("alex")
        .score(3)
        .description("yummy")
        .build();

    restaurant.setMenuItems(Arrays.asList(menuItem));
    restaurant.setReviews(Arrays.asList(review));

    given(restaurantService.getRestaurantById(1004L)).willReturn(restaurant);

    mvc.perform(get("/restaurants/1004"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
        .andExpect(content().string(containsString("\"id\":1004")))
        .andExpect(content().string(containsString("Kimchi")))
        .andExpect(content().string(containsString("yummy")));
  }

  @Test
  public void detailWithNonExistedData() throws Exception {
    given(restaurantService.getRestaurantById(999L))
        .willThrow(new RestaurantNotFoundException(999L));

    mvc.perform(get("/restaurants/999"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("{}"));
  }

  @Test
  public void createWithValidData() throws Exception {
    given(restaurantService.addRestaurant(any())).will(invocation -> {
      Restaurant restaurant = invocation.getArgument(0);
      return Restaurant.builder()
          .id(1234L)
          .name(restaurant.getName())
          .address(restaurant.getAddress())
          .build();
    });

    mvc.perform(post("/restaurants")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"BeRyong\", \"address\": \"Seoul\"}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("location", "/restaurants/1234"))
        .andExpect(content().string("{}"));

    verify(restaurantService).addRestaurant(any());
  }

  @Test
  public void createWithInvalidData() throws Exception {
    mvc.perform(post("/restaurants")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"\", \"address\": \"\"}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void updateWithValidData() throws Exception {
    mvc.perform(patch("/restaurants/1004")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"Joker Bar\", \"address\": \"Busan\"}"))
        .andExpect(status().isOk());

    verify(restaurantService).updateRestaurant(1004L, "Joker Bar", "Busan");
  }

  @Test
  public void updateWithInvalidData() throws Exception {
    mvc.perform(patch("/restaurants/1004")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"\", \"address\": \"\"}"))
        .andExpect(status().isBadRequest());
  }
}