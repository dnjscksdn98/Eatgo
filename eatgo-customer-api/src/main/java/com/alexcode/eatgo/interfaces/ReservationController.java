package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReservationService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.ReservationRequestDto;
import com.alexcode.eatgo.interfaces.dto.ReservationResponseDto;
import com.alexcode.eatgo.security.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customer/api/v1/restaurants")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping(path = "/{restaurantId}/reservations")
    @PreAuthorize("hasAuthority('reservation:write')")
    public SuccessResponse<ReservationResponseDto> create(
            Authentication authentication,
            @PathVariable Long restaurantId,
            @Valid @RequestBody ReservationRequestDto request) {

        ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
        Long userId = applicationUser.getUserId();
        String username = applicationUser.getName();

        String date = request.getDate();
        String time = request.getTime();
        Integer partySize = request.getPartySize();

        return reservationService.create(restaurantId, userId, username, date, time, partySize);
    }
}
