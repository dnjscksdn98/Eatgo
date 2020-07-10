package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.RestaurantCreateRequestDto;
import com.alexcode.eatgo.interfaces.dto.RestaurantResponseDto;
import com.alexcode.eatgo.interfaces.dto.RestaurantUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "management/api/v1/restaurants")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping
  @PreAuthorize("hasAuthority('restaurant:read')")
  public SuccessResponse<List<RestaurantResponseDto>> list() {
    return restaurantService.getRestaurants();
  }

  @GetMapping(path = "/{restaurantId}")
  @PreAuthorize("hasAuthority('restaurant:read')")
  public SuccessResponse<RestaurantResponseDto> detail(@PathVariable("restaurantId") Long restaurantId) {
    return restaurantService.detail(restaurantId);
  }

  @PostMapping
  @PreAuthorize("hasAuthority('restaurant:write')")
  public SuccessResponse<RestaurantResponseDto> create(@Valid @RequestBody RestaurantCreateRequestDto request) {
    return restaurantService.create(request);
  }

  @PatchMapping(path = "/{restaurantId}")
  @PreAuthorize("hasAuthority('restaurant:write')")
  public SuccessResponse<RestaurantResponseDto> update(
          @PathVariable("restaurantId") Long restaurantId,
          @Valid @RequestBody RestaurantUpdateRequestDto request) {

    return restaurantService.update(request, restaurantId);
  }
}
