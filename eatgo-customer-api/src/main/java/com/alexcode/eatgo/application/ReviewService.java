package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.models.Review;
import com.alexcode.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

  private ReviewRepository reviewRepository;

  @Autowired
  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public Review addReview(Long restaurantId, String name, Integer score, String description) {
    Review review = Review.builder()
            .restaurantId(restaurantId)
            .name(name)
            .score(score)
            .description(description)
            .build();
    return reviewRepository.save(review);
  }
}
