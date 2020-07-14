package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.network.SuccessCode;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.RestaurantResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

  private RestaurantRepository restaurantRepository;

  @Autowired
  public RestaurantService(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  public SuccessResponse<List<RestaurantResponseDto>> getRestaurants(Long regionId, Long categoryId) {
    List<Restaurant> restaurants = restaurantRepository.findAllByRegionIdAndCategoryId(regionId, categoryId);

    return response(restaurants, HttpStatus.OK.value(), SuccessCode.OK);
  }

  public SuccessResponse<RestaurantResponseDto> getRestaurantById(Long restaurantId) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    return response(restaurant, HttpStatus.OK.value(), SuccessCode.OK);
  }

  private SuccessResponse<RestaurantResponseDto> response(Restaurant restaurant, Integer status, SuccessCode successCode) {
    RestaurantResponseDto data = RestaurantResponseDto.builder()
            .id(restaurant.getId())
            .name(restaurant.getName())
            .address(restaurant.getAddress())
            .content(restaurant.getContent())
            .categoryName(restaurant.getCategory().getName())
            .regionName(restaurant.getRegion().getName())
            .userName(restaurant.getUser().getName())
            .menuItems(restaurant.getMenuItems())
            .reviews(restaurant.getReviews())
            .build();

    return SuccessResponse.OK(data, status, successCode);
  }

  private SuccessResponse<List<RestaurantResponseDto>> response(List<Restaurant> restaurants, Integer status, SuccessCode successCode) {
    List<RestaurantResponseDto> data = restaurants.stream()
            .map(restaurant -> RestaurantResponseDto.builder()
                      .id(restaurant.getId())
                      .name(restaurant.getName())
                      .address(restaurant.getAddress())
                      .content(restaurant.getContent())
                      .categoryName(restaurant.getCategory().getName())
                      .regionName(restaurant.getRegion().getName())
                      .userName(restaurant.getUser().getName())
                      .build())
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status, successCode);
  }
}
