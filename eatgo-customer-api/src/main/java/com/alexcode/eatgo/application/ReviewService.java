package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.repository.RestaurantRepository;
import com.alexcode.eatgo.domain.repository.ReviewRepository;
import com.alexcode.eatgo.domain.repository.UserRepository;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.exceptions.UserNotFoundException;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.models.Review;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.network.SuccessCode;
import com.alexcode.eatgo.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.ReviewRequestDto;
import com.alexcode.eatgo.interfaces.dto.ReviewResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewService {

  private final UserRepository userRepository;
  private final ReviewRepository reviewRepository;
  private final RestaurantRepository restaurantRepository;

  @Autowired
  public ReviewService(
          UserRepository userRepository,
          ReviewRepository reviewRepository,
          RestaurantRepository restaurantRepository) {

    this.userRepository = userRepository;
    this.reviewRepository = reviewRepository;
    this.restaurantRepository = restaurantRepository;
  }

  public SuccessResponse<List<ReviewResponseDto>> list(Long restaurantId, Long userId) {
    restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

    List<Review> reviews = reviewRepository.findAllByRestaurantIdAndUserId(restaurantId, userId);

    return response(reviews, HttpStatus.OK.value(), SuccessCode.OK);
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
            .restaurantName(review.getRestaurant().getName())
            .build();

    return SuccessResponse.OK(data, status, successCode);
  }

  private SuccessResponse<List<ReviewResponseDto>> response(List<Review> reviews, Integer status, SuccessCode successCode) {
    List<ReviewResponseDto> data = reviews.stream()
            .map(review -> ReviewResponseDto.builder()
                    .id(review.getId())
                    .score(review.getScore())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .restaurantName(review.getRestaurant().getName())
                    .build())
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status, successCode);
  }
}
