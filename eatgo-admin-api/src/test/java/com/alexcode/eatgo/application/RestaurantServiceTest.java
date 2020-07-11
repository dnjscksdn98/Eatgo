package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.models.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {

  private RestaurantService restaurantService;

  @Mock
  private RestaurantRepository restaurantRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockRestaurantRepository();

//    restaurantService = new RestaurantService(restaurantRepository);
  }

  private void mockRestaurantRepository() {
    List<Restaurant> restaurants = new ArrayList<>();
//    Restaurant restaurant = Restaurant.builder()
//            .id(1004L)
//            .name("TestName")
//            .address("TestAddress")
//            .categoryId(1L)
//            .build();
//    restaurants.add(restaurant);

//    given(restaurantRepository.findAll()).willReturn(restaurants);
//    given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
  }

  @Test
  public void getRestaurants() {
//    List<Restaurant> restaurants = restaurantService.getRestaurants();
//
//    assertEquals(restaurants.get(0).getName(), "TestName");
  }

  @Test
  public void getRestaurantWithValidId() {
//    Restaurant restaurant = restaurantService.getRestaurantById(1004L);
//
//    assertEquals(restaurant.getId(), 1004L);
  }

  @Test
  public void getRestaurantWithInvalidId() {
//    assertThrows(RestaurantNotFoundException.class, () -> {
//      restaurantService.getRestaurantById(999L);
//    });
  }

  @Test
  public void addRestaurant() {
    Restaurant restaurant = Restaurant.builder()
            .name("TestName")
            .address("TestAddress")
            .build();

    given(restaurantRepository.save(any()))
            .willReturn(restaurant);

//    Restaurant created = restaurantService.addRestaurant(
//            restaurant.getName(), restaurant.getAddress(), restaurant.getCategoryId());
//
//    assertEquals(created.getName(), "TestName");
//    assertEquals(created.getAddress(), "TestAddress");
  }

  @Test
  public void updateRestaurant() {
//    Restaurant restaurant = Restaurant.builder()
//            .id(1004L)
//            .name("TestName")
//            .address("TestAddress")
//            .categoryId(1L)
//            .build();
//
//    Restaurant updated = restaurantService.updateRestaurant(
//            restaurant.getId(), "UpdateName", "UpdateAddress");

//    assertEquals(updated.getName(), "UpdateName");
//    assertEquals(updated.getAddress(), "UpdateAddress");
  }
}