package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReviewService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.ReviewRequestDto;
import com.alexcode.eatgo.interfaces.dto.ReviewResponseDto;
import com.alexcode.eatgo.security.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customer/api/v1/restaurants")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping(path = "/{restaurantId}/reviews")
    @PreAuthorize("hasAuthority('review:write')")
    public SuccessResponse<ReviewResponseDto> create(
            Authentication authentication,
            @Valid @RequestBody ReviewRequestDto request,
            @PathVariable("restaurantId") Long restaurantId) {

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        Long userId = applicationUser.getUserId();
        String username = applicationUser.getName();

        Double score = request.getScore();
        String content = request.getContent();

        return reviewService.create(
                restaurantId,
                userId,
                username,
                score,
                content);
    }
}
