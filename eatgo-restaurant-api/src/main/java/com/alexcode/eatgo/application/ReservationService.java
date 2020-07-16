package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.models.Reservation;
import com.alexcode.eatgo.domain.repository.ReservationRepository;
import com.alexcode.eatgo.exceptions.ReservationNotFoundException;
import com.alexcode.eatgo.interfaces.dto.ReservationResponseDto;
import com.alexcode.eatgo.network.SuccessCode;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;


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

        return response(reservations, OK.value(), SuccessCode.OK);
    }

    public SuccessResponse<ReservationResponseDto> update(Long reservationId, Long restaurantId) {
        Reservation reservation = findByIdAndRestaurantId(reservationId, restaurantId);
        reservation.update();

        return response(reservation, OK.value(), SuccessCode.RESERVATION_ACCEPT);
    }

    public SuccessResponse<ReservationResponseDto> delete(Long reservationId, Long restaurantId) {
        Reservation reservation = findByIdAndRestaurantId(reservationId, restaurantId);
        reservation.refuse();

        return response(reservation, OK.value(), SuccessCode.RESERVATION_REFUSE);
    }

    private Reservation findByIdAndRestaurantId(Long reservationId, Long restaurantId) {
        Reservation reservation = reservationRepository.findByIdAndRestaurantId(reservationId, restaurantId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        return reservation;
    }

    private SuccessResponse<ReservationResponseDto> response(Reservation reservation, Integer status, SuccessCode successCode) {
        ReservationResponseDto data = ReservationResponseDto.builder()
                .id(reservation.getId())
                .partySize(reservation.getPartySize())
                .status(reservation.getStatus())
                .bookedAt(reservation.getBookedAt())
                .createdAt(reservation.getCreatedAt())
                .createdBy(reservation.getCreatedBy())
                .updatedAt(reservation.getUpdatedAt())
                .updatedBy(reservation.getUpdatedBy())
                .build();

        return SuccessResponse.OK(data, status, successCode);
    }

    private SuccessResponse<List<ReservationResponseDto>> response(List<Reservation> reservations, Integer status, SuccessCode successCode) {
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
                        .build())
                .collect(Collectors.toList());

        return SuccessResponse.OK(data, status, successCode);
    }
}
