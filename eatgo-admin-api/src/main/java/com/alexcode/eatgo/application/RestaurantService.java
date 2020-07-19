package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.exceptions.UserNotFoundException;
import com.alexcode.eatgo.domain.models.Category;
import com.alexcode.eatgo.domain.models.Region;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.repository.CategoryRepository;
import com.alexcode.eatgo.domain.repository.RegionRepository;
import com.alexcode.eatgo.domain.repository.RestaurantRepository;
import com.alexcode.eatgo.domain.repository.UserRepository;
import com.alexcode.eatgo.domain.status.RestaurantStatus;
import com.alexcode.eatgo.exceptions.CategoryNotFoundException;
import com.alexcode.eatgo.exceptions.RegionNotFoundException;
import com.alexcode.eatgo.interfaces.dto.RestaurantCreateRequestDto;
import com.alexcode.eatgo.interfaces.dto.RestaurantResponseDto;
import com.alexcode.eatgo.interfaces.dto.RestaurantUpdateRequestDto;
import com.alexcode.eatgo.network.SuccessCode;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.alexcode.eatgo.security.ApplicationUserRole.ADMIN;

@Service
@Transactional
public class RestaurantService {

  private RestaurantRepository restaurantRepository;
  private CategoryRepository categoryRepository;
  private RegionRepository regionRepository;
  private UserRepository userRepository;

  @Autowired
  public RestaurantService(
          RestaurantRepository restaurantRepository,
          CategoryRepository categoryRepository,
          RegionRepository regionRepository,
          UserRepository userRepository) {

    this.restaurantRepository = restaurantRepository;
    this.categoryRepository = categoryRepository;
    this.regionRepository = regionRepository;
    this.userRepository = userRepository;
  }

  public SuccessResponse<List<RestaurantResponseDto>> list() {
    List<Restaurant> restaurants = restaurantRepository.findAll();
    return response(restaurants, HttpStatus.OK.value(), SuccessCode.OK);
  }

  public SuccessResponse<RestaurantResponseDto> detail(Long restaurantId) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    return response(restaurant, HttpStatus.OK.value(), SuccessCode.OK);
  }

  public SuccessResponse<RestaurantResponseDto> create(RestaurantCreateRequestDto request) {
    Long categoryId = request.getCategoryId();
    Long regionId = request.getRegionId();
    Long userId = request.getUserId();

    Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));

    Region region = regionRepository.findById(regionId)
            .orElseThrow(() -> new RegionNotFoundException(regionId));

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

    Restaurant restaurant = Restaurant.builder()
            .name(request.getName())
            .address(request.getAddress())
            .status(RestaurantStatus.REGISTERED)
            .content(request.getContent())
            .createdAt(LocalDateTime.now())
            .createdBy(ADMIN.name())
            .category(category)
            .region(region)
            .user(user)
            .build();

    Restaurant savedRestaurant = restaurantRepository.save(restaurant);

    return response(savedRestaurant, HttpStatus.CREATED.value(), SuccessCode.RESTAURANT_CREATION_SUCCESS);
  }

  public SuccessResponse<RestaurantResponseDto> update(RestaurantUpdateRequestDto request, Long restaurantId) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    restaurant.updateRestaurant(
            request.getName(),
            request.getAddress(),
            request.getStatus(),
            request.getContent()
    );

    return response(restaurant, HttpStatus.OK.value(), SuccessCode.RESTAURANT_UPDATE_SUCCESS);
  }

  private SuccessResponse<List<RestaurantResponseDto>> response(List<Restaurant> restaurants, Integer status, SuccessCode successCode) {
    List<RestaurantResponseDto> data = restaurants.stream()
            .map(restaurant -> RestaurantResponseDto.builder()
                    .id(restaurant.getId())
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .status(restaurant.getStatus())
                    .content(restaurant.getContent())
                    .createdAt(restaurant.getCreatedAt())
                    .createdBy(restaurant.getCreatedBy())
                    .updatedAt(restaurant.getUpdatedAt())
                    .updatedBy(restaurant.getUpdatedBy())
                    .categoryName(restaurant.getCategory().getName())
                    .regionName(restaurant.getRegion().getName())
                    .owner(restaurant.getUser().getName())
                    .build())
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status, successCode);
  }

  private SuccessResponse<RestaurantResponseDto> response(Restaurant restaurant, Integer status, SuccessCode successCode) {
    RestaurantResponseDto data = RestaurantResponseDto.builder()
            .id(restaurant.getId())
            .name(restaurant.getName())
            .address(restaurant.getAddress())
            .status(restaurant.getStatus())
            .content(restaurant.getContent())
            .createdAt(restaurant.getCreatedAt())
            .createdBy(restaurant.getCreatedBy())
            .updatedAt(restaurant.getUpdatedAt())
            .updatedBy(restaurant.getUpdatedBy())
            .categoryName(restaurant.getCategory().getName())
            .regionName(restaurant.getRegion().getName())
            .owner(restaurant.getUser().getName())
            .menuItems(restaurant.getMenuItems())
            .reviews(restaurant.getReviews())
            .reservations(restaurant.getReservations())
            .build();

    return SuccessResponse.OK(data, status, successCode);
  }
}
