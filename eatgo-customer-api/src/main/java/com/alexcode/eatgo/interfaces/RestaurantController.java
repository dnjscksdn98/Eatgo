package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.models.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer/api/v1/restaurants")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping
  @PreAuthorize("hasAuthority('restaurant:read')")
  public List<Restaurant> list(
          @RequestParam String region,
          @RequestParam Long categoryId) {

    return restaurantService.getRestaurants(region, categoryId);
  }

  @GetMapping(path = "/{restaurantId}")
  @PreAuthorize("hasAuthority('restaurant:read')")
  public Restaurant detail(@PathVariable("restaurantId") Long restaurantId) {
    return restaurantService.getRestaurantById(restaurantId);
  }
}
