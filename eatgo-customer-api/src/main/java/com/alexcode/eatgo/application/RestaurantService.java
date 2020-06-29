package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.MenuItemRepository;
import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.ReviewRepository;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.models.MenuItem;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

  private RestaurantRepository restaurantRepository;
  private MenuItemRepository menuItemRepository;
  private ReviewRepository reviewRepository;

  @Autowired
  public RestaurantService(
          RestaurantRepository restaurantRepository,
          MenuItemRepository menuItemRepository,
          ReviewRepository reviewRepository) {

    this.restaurantRepository = restaurantRepository;
    this.menuItemRepository = menuItemRepository;
    this.reviewRepository = reviewRepository;
  }

  public List<Restaurant> getRestaurants(String region, Long categoryId) {
    return restaurantRepository.findAllByAddressContainingAndCategoryId(region, categoryId);
  }

  public Restaurant getRestaurantById(Long restaurantId) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(restaurantId);
    restaurant.setMenuItems(menuItems);

    List<Review> reviews = reviewRepository.findAllByRestaurantId(restaurantId);
    restaurant.setReviews(reviews);

    return restaurant;
  }
}
