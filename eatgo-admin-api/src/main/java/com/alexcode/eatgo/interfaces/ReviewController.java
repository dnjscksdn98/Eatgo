package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReviewService;
import com.alexcode.eatgo.domain.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/api/v1/reviews")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  @GetMapping
  @PreAuthorize("hasAuthority('review:read')")
  public List<Review> list() {
    return reviewService.getReviews();
  }
}
