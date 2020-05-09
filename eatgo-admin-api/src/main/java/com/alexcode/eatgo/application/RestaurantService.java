package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.Restaurant;
import com.alexcode.eatgo.domain.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.RestaurantRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

  private RestaurantRepository restaurantRepository;

  @Autowired
  public RestaurantService(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  public List<Restaurant> getRestaurants() {
    return restaurantRepository.findAll();
  }

  public Restaurant getRestaurantById(Long restaurantId) {
    return restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
  }

  public Restaurant addRestaurant(Restaurant restaurant) {
    return restaurantRepository.save(restaurant);
  }

  @Transactional
  public Restaurant updateRestaurant(Long restaurantId, String name, String address) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    restaurant.updateInformation(name, address);

    return Restaurant.builder()
        .id(restaurantId)
        .name(name)
        .address(address)
        .build();
  }
}
