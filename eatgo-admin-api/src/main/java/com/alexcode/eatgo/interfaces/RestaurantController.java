package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.interfaces.dto.RestaurantCreateDto;
import com.alexcode.eatgo.interfaces.dto.RestaurantUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping("/restaurants")
  public List<Restaurant> list() {
    return restaurantService.getRestaurants();
  }

  @GetMapping("/restaurants/{restaurantId}")
  public Restaurant detail(@PathVariable("restaurantId") Long restaurantId) {
    return restaurantService.getRestaurantById(restaurantId);
  }

  @PostMapping("/restaurants")
  public ResponseEntity<?> create(
          @Valid @RequestBody RestaurantCreateDto resource) throws URISyntaxException {

    Restaurant restaurant = restaurantService.addRestaurant(
            resource.getName(),
            resource.getAddress(),
            resource.getCategoryId()
    );
    URI location = new URI("/restaurants/" + restaurant.getId());

    return ResponseEntity.created(location).body("{}");
  }

  @PatchMapping("/restaurants/{restaurantId}")
  public String update(
          @PathVariable("restaurantId") Long restaurantId,
          @Valid @RequestBody RestaurantUpdateDto resource) {

    restaurantService.updateRestaurant(
        restaurantId,
        resource.getName(),
        resource.getAddress()
    );

    return "{}";
  }
}
