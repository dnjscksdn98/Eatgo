package com.alexcode.eatgo.application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.alexcode.eatgo.domain.MenuItem;
import com.alexcode.eatgo.domain.MenuItemRepository;
import com.alexcode.eatgo.domain.Restaurant;
import com.alexcode.eatgo.domain.RestaurantRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RestaurantServiceTest {

  private RestaurantService restaurantService;

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private MenuItemRepository menuItemRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    mockRestaurantRepository();
    mockMenuItemRepository();

    restaurantService = new RestaurantService(
        restaurantRepository, menuItemRepository);
  }

  private void mockRestaurantRepository() {
    List<Restaurant> restaurants = new ArrayList<>();
    Restaurant restaurant = Restaurant.builder()
        .id(1004L)
        .name("Bob zip")
        .address("Seoul")
        .build();

    restaurants.add(restaurant);
    given(restaurantRepository.findAll()).willReturn(restaurants);

    given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
  }

  private void mockMenuItemRepository() {
    List<MenuItem> menuItems = new ArrayList<>();
    menuItems.add(MenuItem.builder()
        .name("Kimchi")
        .build()
    );
    given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
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

  @Test
  public void addRestaurant() {
    given(restaurantRepository.save(any())).will(invocation -> {
      Restaurant restaurant = invocation.getArgument(0);
      restaurant.setId(1234L);
      return restaurant;
    });

    Restaurant restaurant = Restaurant.builder()
        .name("BeRyong")
        .address("Seoul")
        .build();

    Restaurant created = restaurantService.addRestaurant(restaurant);

    assertThat(created.getId(), is(1234L));
  }

  @Test
  public void updateRestaurant() {
    Restaurant restaurant = Restaurant.builder()
        .id(1004L)
        .name("Bob zip")
        .address("Seoul")
        .build();

    given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

    restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");

    assertThat(restaurant.getName(), is("Sool zip"));
    assertThat(restaurant.getAddress(), is("Busan"));
  }
}