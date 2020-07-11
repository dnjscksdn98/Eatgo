package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.ReservationRepository;
import com.alexcode.eatgo.domain.models.Reservation;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.ReservationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public SuccessResponse<List<ReservationResponseDto>> list(Long restaurantId) {
        List<Reservation> reservations = reservationRepository.findAllByRestaurantId(restaurantId);

        return response(reservations, 200);
    }

    private SuccessResponse<List<ReservationResponseDto>> response(List<Reservation> reservations, Integer status) {
        List<ReservationResponseDto> data = reservations.stream()
                .map(reservation -> ReservationResponseDto.builder()
                        .id(reservation.getId())
                        .partySize(reservation.getPartySize())
                        .status(reservation.getStatus())
                        .bookedAt(reservation.getBookedAt())
                        .createdAt(reservation.getCreatedAt())
                        .createdBy(reservation.getCreatedBy())
                        .updatedAt(reservation.getUpdatedAt())
                        .updatedBy(reservation.getUpdatedBy())
                        .userId(reservation.getUser().getId())
                        .build())
                .collect(Collectors.toList());

        return SuccessResponse.OK(data, status);
    }
}
