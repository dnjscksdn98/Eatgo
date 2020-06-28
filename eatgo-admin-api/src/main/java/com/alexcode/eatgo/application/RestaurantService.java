package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.RestaurantRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
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

  public Restaurant addRestaurant(String name, String address, Long categoryId) {
    Restaurant restaurant = Restaurant.builder()
            .name(name)
            .address(address)
            .categoryId(categoryId)
            .build();
    return restaurantRepository.save(restaurant);
  }

  public Restaurant updateRestaurant(Long restaurantId, String name, String address) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    restaurant.updateInformation(name, address);

    return restaurant;
  }
}
