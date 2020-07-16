package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "customer/api/v1/restaurants")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('restaurant:read')")
  public ResponseEntity<SuccessResponse> list(
          @RequestParam Long regionId,
          @RequestParam Long categoryId) {

    SuccessResponse body = restaurantService.getRestaurants(regionId, categoryId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @GetMapping(path = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('restaurant:read')")
  public ResponseEntity<SuccessResponse> detail(@PathVariable("restaurantId") Long restaurantId) {
    SuccessResponse body = restaurantService.getRestaurantById(restaurantId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

}
