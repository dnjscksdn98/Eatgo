package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.RestaurantResponseDto;
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
  public SuccessResponse<List<RestaurantResponseDto>> list(
          @RequestParam Long regionId,
          @RequestParam Long categoryId) {

    return restaurantService.getRestaurants(regionId, categoryId);
  }

  @GetMapping(path = "/{restaurantId}")
  @PreAuthorize("hasAuthority('restaurant:read')")
  public SuccessResponse<RestaurantResponseDto> detail(@PathVariable("restaurantId") Long restaurantId) {
    return restaurantService.getRestaurantById(restaurantId);
  }

}
