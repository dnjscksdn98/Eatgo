package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.Review;
import com.alexcode.eatgo.domain.ReviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public List<Review> getReviews() {
    return reviewRepository.findAll();
  }
}
