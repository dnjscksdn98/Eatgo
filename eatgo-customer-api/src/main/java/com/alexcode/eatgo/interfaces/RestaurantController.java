package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReservationService;
import com.alexcode.eatgo.application.RestaurantService;
import com.alexcode.eatgo.application.ReviewService;
import com.alexcode.eatgo.domain.models.Reservation;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.models.Review;
import com.alexcode.eatgo.interfaces.dto.ReservationDto;
import com.alexcode.eatgo.interfaces.dto.ReviewDto;
import com.alexcode.eatgo.security.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("customer/api/v1/restaurants")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private ReviewService reviewService;

  @GetMapping
  @PreAuthorize("hasAuthority('restaurant:read')")
  public List<Restaurant> restaurantList(
          @RequestParam String region,
          @RequestParam Long categoryId) {

    return restaurantService.getRestaurants(region, categoryId);
  }

  @GetMapping(path = "/{restaurantId}")
  @PreAuthorize("hasAuthority('restaurant:read')")
  public Restaurant restaurantDetail(@PathVariable("restaurantId") Long restaurantId) {
    return restaurantService.getRestaurantById(restaurantId);
  }

  @PostMapping(path = "/{restaurantId}/reservations")
  @PreAuthorize("hasAuthority('reservation:write')")
  public ResponseEntity<?> createReservation(
          Authentication authentication,
          @PathVariable Long restaurantId,
          @Valid @RequestBody ReservationDto resource
  ) throws URISyntaxException {

    ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
    Long userId = applicationUser.getUserId();
    String userName = applicationUser.getName();

    String date = resource.getDate();
    String time = resource.getTime();
    Integer partySize = resource.getPartySize();

    Reservation reservation = reservationService.addReservation(restaurantId, userId, userName, date, time, partySize);

    URI location = new URI("/restaurants/" + restaurantId + "/reservations/" + reservation.getId());
    return ResponseEntity.created(location).body("{}");
  }

  @PostMapping(path = "/{restaurantId}/reviews")
  @PreAuthorize("hasAuthority('review:write')")
  public ResponseEntity<?> createReview(
          Authentication authentication,
          @Valid @RequestBody ReviewDto resource,
          @PathVariable("restaurantId") Long restaurantId
  ) throws URISyntaxException {

    ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
    String userName = applicationUser.getName();

    Integer score = resource.getScore();
    String description = resource.getDescription();

    Review review = reviewService.addReview(
            restaurantId,
            userName,
            score,
            description);

    URI location = new URI("/restaurants/" + restaurantId + "/reviews/" + review.getId());
    return ResponseEntity.created(location).body("{}");
  }

}
