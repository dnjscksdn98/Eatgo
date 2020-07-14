package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.ReviewRepository;
import com.alexcode.eatgo.domain.UserRepository;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.exceptions.UserNotFoundException;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.models.Review;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.network.SuccessCode;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.ReviewRequestDto;
import com.alexcode.eatgo.interfaces.dto.ReviewResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ReviewService {

  private ReviewRepository reviewRepository;
  private UserRepository userRepository;
  private RestaurantRepository restaurantRepository;

  @Autowired
  public ReviewService(
          ReviewRepository reviewRepository,
          UserRepository userRepository,
          RestaurantRepository restaurantRepository) {

    this.reviewRepository = reviewRepository;
    this.userRepository = userRepository;
    this.restaurantRepository = restaurantRepository;
  }

  public SuccessResponse<ReviewResponseDto> create(
          Long restaurantId, Long userId, String username, ReviewRequestDto request) {

    Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

    Review review = Review.builder()
            .score(request.getScore())
            .content(request.getContent())
            .createdAt(LocalDateTime.now())
            .createdBy(username)
            .restaurant(restaurant)
            .user(user)
            .build();

    Review savedReview = reviewRepository.save(review);

    return response(savedReview, HttpStatus.CREATED.value(), SuccessCode.REVIEW_SUCCESS);
  }

  private SuccessResponse<ReviewResponseDto> response(Review review, Integer status, SuccessCode successCode) {
    ReviewResponseDto data = ReviewResponseDto.builder()
            .id(review.getId())
            .score(review.getScore())
            .content(review.getContent())
            .createdAt(review.getCreatedAt())
            .createdBy(review.getCreatedBy())
            .restaurantId(review.getRestaurant().getId())
            .userId(review.getUser().getId())
            .build();

    return SuccessResponse.OK(data, status, successCode);
  }
}
