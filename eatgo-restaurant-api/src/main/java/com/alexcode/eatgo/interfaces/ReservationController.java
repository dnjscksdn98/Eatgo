package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReservationService;
import com.alexcode.eatgo.domain.models.Reservation;
import com.alexcode.eatgo.security.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("owner/api/v1")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(path = "/reservations")
    @PreAuthorize("hasAuthority('reservation:read')")
    public List<Reservation> list(Authentication authentication) {

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        Long restaurantId = applicationUser.getRestaurantId();

        List<Reservation> reservations = reservationService.getReservations(restaurantId);
        return reservations;
    }
}
