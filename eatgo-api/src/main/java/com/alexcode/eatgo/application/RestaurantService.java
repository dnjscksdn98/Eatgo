package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.MenuItem;
import com.alexcode.eatgo.domain.MenuItemRepository;
import com.alexcode.eatgo.domain.Restaurant;
import com.alexcode.eatgo.domain.RestaurantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

  @Autowired
  RestaurantRepository restaurantRepository;

  @Autowired
  MenuItemRepository menuItemRepository;

  public RestaurantService(RestaurantRepository restaurantRepository,
                            MenuItemRepository menuItemRepository) {
    this.restaurantRepository = restaurantRepository;
    this.menuItemRepository = menuItemRepository;
  }

  public List<Restaurant> getRestaurants() {
    return restaurantRepository.findAll();
  }

  public Restaurant getRestaurantById(Long id) {
    Restaurant restaurant = restaurantRepository.findById(id);
    List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
    restaurant.setMenuItems(menuItems);
    return restaurant;
  }

  public void addRestaurant(Restaurant restaurant) {
  }
}
