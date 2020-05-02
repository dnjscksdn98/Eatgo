package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.MenuItem;
import com.alexcode.eatgo.domain.MenuItemRepository;
import com.alexcode.eatgo.domain.Restaurant;
import com.alexcode.eatgo.domain.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.Review;
import com.alexcode.eatgo.domain.ReviewRepository;
import java.util.List;
import javax.transaction.Transactional;
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

  public List<Restaurant> getRestaurants() {
    return restaurantRepository.findAll();
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

  public Restaurant addRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }

  @Transactional
  public Restaurant updateRestaurant(Long id, String name, String address) {
    Restaurant restaurant = restaurantRepository.findById(id)
        .orElseThrow(() -> new RestaurantNotFoundException(id));

    restaurant.updateInformation(name, address);

    return Restaurant.builder()
        .id(id)
        .name(name)
        .address(address)
        .build();
  }
}
