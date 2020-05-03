package com.alexcode.eatgo.application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alexcode.eatgo.domain.MenuItem;
import com.alexcode.eatgo.domain.MenuItemRepository;
import com.alexcode.eatgo.domain.Restaurant;
import com.alexcode.eatgo.domain.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.Review;
import com.alexcode.eatgo.domain.ReviewRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
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

  @Mock
  private ReviewRepository reviewRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    mockRestaurantRepository();
    mockMenuItemRepository();
    mockReviewRepository();

    restaurantService = new RestaurantService(
        restaurantRepository,
        menuItemRepository,
        reviewRepository
    );
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

  private void mockReviewRepository() {
    List<Review> reviews = new ArrayList<>();
    reviews.add(Review.builder()
        .name("alex")
        .score(3)
        .description("yummy")
        .build()
    );
    given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
  }

  @Test
  public void getRestaurants() {
    List<Restaurant> restaurants = restaurantService.getRestaurants();

    assertThat(restaurants.get(0).getId(), is(1004L));
  }

  @Test
  public void getRestaurantWithExistedData() {
    Restaurant restaurant = restaurantService.getRestaurantById(1004L);

    verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
    verify(reviewRepository).findAllByRestaurantId(eq(1004L));

    assertThat(restaurant.getId(), is(1004L));

    MenuItem menuItem = restaurant.getMenuItems().get(0);
    assertThat(menuItem.getName(), is("Kimchi"));

    Review review = restaurant.getReviews().get(0);
    assertThat(review.getName(), is("alex"));
  }

  @Test
  public void getRestaurantWithNonExistedData() {
    Assertions.assertThrows(RestaurantNotFoundException.class, () -> {
      restaurantService.getRestaurantById(999L);
    });
  }
}