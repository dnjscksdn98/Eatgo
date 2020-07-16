package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.repository.CategoryRepository;
import com.alexcode.eatgo.domain.repository.RegionRepository;
import com.alexcode.eatgo.domain.repository.RestaurantRepository;
import com.alexcode.eatgo.domain.repository.UserRepository;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.network.SuccessResponse;
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

  @Mock
  private CategoryRepository categoryRepository;

  @Mock
  private RegionRepository regionRepository;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockRestaurantRepository();

    restaurantService = new RestaurantService(
            restaurantRepository,
            categoryRepository,
            regionRepository,
            userRepository
    );
  }

  private void mockRestaurantRepository() {
    List<Restaurant> restaurants = new ArrayList<>();
    Restaurant restaurant = Restaurant.builder()
            .id(1004L)
            .name("TestName")
            .address("TestAddress")
            .build();
    restaurants.add(restaurant);

    given(restaurantRepository.findAll()).willReturn(restaurants);
    given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
  }

  @Test
  public void getRestaurants() {
    SuccessResponse response = restaurantService.list();
    List<Restaurant> restaurants = (List<Restaurant>) response.getData();

    assertEquals(restaurants.get(0).getName(), "TestName");
  }

  @Test
  public void getRestaurantWithValidId() {
    SuccessResponse response = restaurantService.detail(1004L);
    Restaurant restaurant = (Restaurant) response.getData();

    assertEquals(restaurant.getId(), 1004L);
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