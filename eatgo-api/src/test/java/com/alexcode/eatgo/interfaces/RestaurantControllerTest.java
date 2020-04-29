package com.alexcode.eatgo.interfaces;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.MenuItem;
import com.alexcode.eatgo.domain.Restaurant;
import java.util.ArrayList;
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
    restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
    given(restaurantService.getRestaurants()).willReturn(restaurants);

    mvc.perform(get("/restaurants"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
        .andExpect(content().string(containsString("\"id\":1004")));
  }

  @Test
  public void detail() throws Exception {
    Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
    restaurant.addMenuItem(new MenuItem("Kimchi"));
    given(restaurantService.getRestaurantById(1004L)).willReturn(restaurant);

    mvc.perform(get("/restaurants/1004"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
        .andExpect(content().string(containsString("\"id\":1004")))
        .andExpect(content().string(containsString("Kimchi")));
  }
}