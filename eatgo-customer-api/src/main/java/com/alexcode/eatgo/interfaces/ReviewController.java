package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReviewService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.ReviewRequestDto;
import com.alexcode.eatgo.security.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "customer/api/v1/restaurants")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping(path = "/{restaurantId}/reviews")
    @PreAuthorize("hasAuthority('review:write')")
    public ResponseEntity<SuccessResponse> create(
            Authentication authentication,
            @Valid @RequestBody ReviewRequestDto request,
            @PathVariable("restaurantId") Long restaurantId) {

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        Long userId = applicationUser.getUserId();
        String username = applicationUser.getName();

        SuccessResponse body = reviewService.create(restaurantId, userId, username, request);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
