package com.alexcode.eatgo.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.alexcode.eatgo.domain.repository.RestaurantRepository;
import com.alexcode.eatgo.domain.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ReviewServiceTest {

  private ReviewService reviewService;

  @Mock
  private ReviewRepository reviewRepository;

  @Mock
  private RestaurantRepository restaurantRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

//    reviewService = new ReviewService(reviewRepository, restaurantRepository);
  }

  @Test
  public void addReview() {
//    reviewService.addReview(1004L, "tester", 3.5, "yummy");

    verify(reviewRepository).save(any());
  }

}