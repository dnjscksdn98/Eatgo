package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.models.Review;
import com.alexcode.eatgo.domain.repository.ReviewRepository;
import com.alexcode.eatgo.interfaces.dto.ReviewResponseDto;
import com.alexcode.eatgo.network.SuccessCode;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewService {

  private ReviewRepository reviewRepository;

  @Autowired
  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public SuccessResponse<List<ReviewResponseDto>> list() {
    List<Review> reviews = reviewRepository.findAll();
    return response(reviews, HttpStatus.OK.value(), SuccessCode.OK);
  }

  private SuccessResponse<List<ReviewResponseDto>> response(List<Review> reviews, Integer status, SuccessCode successCode) {
    List<ReviewResponseDto> data = reviews.stream()
            .map(review -> ReviewResponseDto.builder()
                    .id(review.getId())
                    .score(review.getScore())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .createdBy(review.getCreatedBy())
                    .restaurantId(review.getRestaurant().getId())
                    .userId(review.getUser().getId())
                    .build())
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status, successCode);
  }
}
