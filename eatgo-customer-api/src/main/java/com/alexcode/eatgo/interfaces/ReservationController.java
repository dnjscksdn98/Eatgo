package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReservationService;
import com.alexcode.eatgo.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.ReservationRequestDto;
import com.alexcode.eatgo.security.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "customer/api/v1/restaurants")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping(path = "/{restaurantId}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('reservation:write')")
    public ResponseEntity<SuccessResponse> create(
            Authentication authentication,
            @PathVariable("restaurantId") Long restaurantId,
            @Valid @RequestBody ReservationRequestDto request) {

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        Long userId = applicationUser.getUserId();
        String username = applicationUser.getName();

        SuccessResponse body = reservationService.create(restaurantId, userId, username, request);

        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
