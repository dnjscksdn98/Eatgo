package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReviewService;
import com.alexcode.eatgo.domain.models.Review;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
          Authentication authentication,
          @Valid @RequestBody Review resource,
          @PathVariable("restaurantId") Long restaurantId
  ) throws URISyntaxException {

    Claims claims = (Claims) authentication.getPrincipal();
    String name = claims.get("name", String.class);
    Integer score = resource.getScore();
    String description = resource.getDescription();

    Review review = reviewService.addReview(
            restaurantId,
            name,
            score,
            description);

    URI location = new URI("/restaurants/" + restaurantId + "/reviews/" + review.getId());
    return ResponseEntity.created(location).body("{}");
  }
}
