package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.MenuItem;
import com.alexcode.eatgo.domain.MenuItemRepository;
import com.alexcode.eatgo.domain.Restaurant;
import com.alexcode.eatgo.domain.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.Review;
import com.alexcode.eatgo.domain.ReviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private MenuItemRepository menuItemRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  public RestaurantService(RestaurantRepository restaurantRepository,
                            MenuItemRepository menuItemRepository,
                            ReviewRepository reviewRepository) {

    this.restaurantRepository = restaurantRepository;
    this.menuItemRepository = menuItemRepository;
    this.reviewRepository = reviewRepository;
  }

  public List<Restaurant> getRestaurants(String region, Long categoryId) {
    return restaurantRepository.findAllByAddressContainingAndCategoryId(region, categoryId);
  }

  public Restaurant getRestaurantById(Long id) {
    Restaurant restaurant = restaurantRepository.findById(id)
        .orElseThrow(() -> new RestaurantNotFoundException(id));

    List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
    restaurant.setMenuItems(menuItems);

    List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
    restaurant.setReviews(reviews);

    return restaurant;
  }
}
