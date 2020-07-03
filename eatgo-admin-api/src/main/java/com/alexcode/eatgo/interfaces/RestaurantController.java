package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.interfaces.dto.RestaurantCreateDto;
import com.alexcode.eatgo.interfaces.dto.RestaurantUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("admin/api/v1/restaurants")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping
  @PreAuthorize("hasAuthority('restaurant:read')")
  public List<Restaurant> list() {
    return restaurantService.getRestaurants();
  }

  @GetMapping(path = "/{restaurantId}")
  @PreAuthorize("hasAuthority('restaurant:read')")
  public Restaurant detail(@PathVariable("restaurantId") Long restaurantId) {
    return restaurantService.getRestaurantById(restaurantId);
  }

  @PostMapping
  @PreAuthorize("hasAuthority('restaurant:write')")
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

  @PatchMapping(path = "/{restaurantId}")
  @PreAuthorize("hasAuthority('restaurant:write')")
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
