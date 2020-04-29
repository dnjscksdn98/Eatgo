package com.alexcode.eatgo.application;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.alexcode.eatgo.domain.MenuItem;
import com.alexcode.eatgo.domain.MenuItemRepository;
import com.alexcode.eatgo.domain.MenuItemRepositoryImpl;
import com.alexcode.eatgo.domain.Restaurant;
import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.RestaurantRepositoryImpl;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestaurantServiceTest {

  private RestaurantService restaurantService;
  private RestaurantRepository restaurantRepository;
  private MenuItemRepository menuItemRepository;

  @BeforeEach
  public void setUp() {
    restaurantRepository = new RestaurantRepositoryImpl();
    menuItemRepository = new MenuItemRepositoryImpl();
    restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
  }

  @Test
  public void getRestaurants() {
    List<Restaurant> restaurants = restaurantService.getRestaurants();
    assertThat(restaurants.get(0).getId(), is(1004L));
  }

  @Test
  public void getRestaurant() {
    Restaurant restaurant = restaurantService.getRestaurantById(1004L);
    assertThat(restaurant.getId(), is(1004L));
    MenuItem menuItem = restaurant.getMenuItems().get(0);
    assertThat(menuItem.getName(), is("Kimchi"));
  }
}