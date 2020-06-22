package com.alexcode.eatgo.interfaces;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.models.MenuItem;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.models.Review;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
        .categoryId(1L)
        .name("Bob zip")
        .address("Seoul")
        .build()
    );
    given(restaurantService.getRestaurants("Seoul", 1L)).willReturn(restaurants);

    mvc.perform(get("/restaurants?region=Seoul&category=1"))
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
}