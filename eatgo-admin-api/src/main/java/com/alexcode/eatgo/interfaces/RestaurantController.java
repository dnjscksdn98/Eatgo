package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.interfaces.dto.RestaurantCreateRequestDto;
import com.alexcode.eatgo.interfaces.dto.RestaurantUpdateRequestDto;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "management/api/v1/restaurants")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('restaurant:read')")
  public ResponseEntity<SuccessResponse<?>> list() {
    SuccessResponse<?> body = restaurantService.list();
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @GetMapping(path = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('restaurant:read')")
  public ResponseEntity<SuccessResponse<?>> detail(@PathVariable("restaurantId") Long restaurantId) {
    SuccessResponse<?> body = restaurantService.detail(restaurantId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('restaurant:write')")
  public ResponseEntity<SuccessResponse<?>> create(@Valid @RequestBody RestaurantCreateRequestDto request) {
    SuccessResponse<?> body = restaurantService.create(request);
    return new ResponseEntity<>(body, HttpStatus.CREATED);
  }

  @PatchMapping(path = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('restaurant:write')")
  public ResponseEntity<SuccessResponse<?>> update(
          @PathVariable("restaurantId") Long restaurantId,
          @Valid @RequestBody RestaurantUpdateRequestDto request) {

    SuccessResponse<?> body = restaurantService.update(request, restaurantId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }
}
