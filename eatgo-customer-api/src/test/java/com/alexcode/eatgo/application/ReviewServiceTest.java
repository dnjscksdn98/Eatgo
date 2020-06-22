package com.alexcode.eatgo.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.alexcode.eatgo.domain.models.Review;
import com.alexcode.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ReviewServiceTest {

  private ReviewService reviewService;

  @Mock
  private ReviewRepository reviewRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    reviewService = new ReviewService(reviewRepository);
  }

  @Test
  public void addReview() {
    Review review = Review.builder()
        .name("alex")
        .score(3)
        .description("yummy")
        .build();

    reviewService.addReview(1004L, review);

    verify(reviewRepository).save(any());
  }

}