package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReservationService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.security.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("owner/api/v1")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(path = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('reservation:read')")
    public ResponseEntity<SuccessResponse> list(Authentication authentication) {

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        Long restaurantId = applicationUser.getRestaurantId();

        SuccessResponse body = reservationService.list(restaurantId);

        return new ResponseEntity(body, OK);
    }

    @PatchMapping(path = "/reservations/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('reservation:write')")
    public ResponseEntity<SuccessResponse> update(
            Authentication authentication,
            @PathVariable("reservationId") Long reservationId) {

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        Long restaurantId = applicationUser.getRestaurantId();

        SuccessResponse body = reservationService.update(reservationId, restaurantId);

        return new ResponseEntity(body, OK);
    }

    @DeleteMapping(path = "/reservations/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('reservation:write')")
    public ResponseEntity<SuccessResponse> delete(
            Authentication authentication,
            @PathVariable("reservationId") Long reservationId) {

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        Long restaurantId = applicationUser.getRestaurantId();

        SuccessResponse body = reservationService.delete(reservationId, restaurantId);

        return new ResponseEntity(body, OK);
    }
}
