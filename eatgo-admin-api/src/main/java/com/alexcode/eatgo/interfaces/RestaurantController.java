package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.models.Restaurant;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
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
  public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource) throws URISyntaxException {
    Restaurant restaurant = restaurantService.addRestaurant(
        Restaurant.builder()
            .name(resource.getName())
            .address(resource.getAddress())
            .build()
    );
    URI location = new URI("/restaurants/" + restaurant.getId());
    return ResponseEntity.created(location).body("{}");
  }

  @PatchMapping("/restaurants/{restaurantId}")
  public String update(@PathVariable("restaurantId") Long restaurantId,
                       @Valid @RequestBody Restaurant resource) {
    restaurantService.updateRestaurant(
        restaurantId,
        resource.getName(),
        resource.getAddress()
    );
    return "{}";
  }
}
