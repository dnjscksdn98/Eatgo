package com.alexcode.eatgo.application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import com.alexcode.eatgo.domain.models.Review;
import com.alexcode.eatgo.domain.repository.ReviewRepository;
import java.util.ArrayList;
import java.util.List;
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
  public void getReviews() {
    List<Review> mockReviews = new ArrayList<>();
//    mockReviews.add(Review.builder().description("Cool").build());

    given(reviewRepository.findAll()).willReturn(mockReviews);

//    List<Review> reviews = reviewService.getReviews();
//
//    Review review = reviews.get(0);
//
//    assertThat(review.getDescription(), is("Cool"));
  }
}