package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReviewService;
import com.alexcode.eatgo.domain.Review;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  @PostMapping("/restaurants/{restaurantId}/reviews")
  public ResponseEntity<?> create(
      @Valid @RequestBody Review resource,
      @PathVariable("restaurantId") Long restaurantId
  ) throws URISyntaxException {

    Review review = reviewService.addReview(restaurantId, resource);
    URI location = new URI("/restaurants/" + restaurantId + "/reviews/" + review.getId());

    return ResponseEntity.created(location).body("{}");
  }
}
