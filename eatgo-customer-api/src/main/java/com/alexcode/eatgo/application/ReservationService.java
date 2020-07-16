package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.exceptions.UserNotFoundException;
import com.alexcode.eatgo.domain.models.Reservation;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.repository.ReservationRepository;
import com.alexcode.eatgo.domain.repository.RestaurantRepository;
import com.alexcode.eatgo.domain.repository.UserRepository;
import com.alexcode.eatgo.domain.status.ReservationStatus;
import com.alexcode.eatgo.interfaces.dto.ReservationRequestDto;
import com.alexcode.eatgo.interfaces.dto.ReservationResponseDto;
import com.alexcode.eatgo.network.SuccessCode;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    @Autowired
    public ReservationService(
            ReservationRepository reservationRepository,
            RestaurantRepository restaurantRepository,
            UserRepository userRepository) {

        this.reservationRepository = reservationRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public SuccessResponse<List<ReservationResponseDto>> list(Long userId) {
        List<Reservation> reservations = reservationRepository.findAllByUserId(userId);
        return response(reservations, HttpStatus.OK.value(), SuccessCode.OK);
    }

    public SuccessResponse<ReservationResponseDto> create(
            Long restaurantId, Long userId, String username, ReservationRequestDto request) {

        String str = request.getDate() + " " + request.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime bookedAt = LocalDateTime.parse(str, formatter);

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Reservation reservation = Reservation.builder()
                .partySize(request.getPartySize())
                .status(ReservationStatus.WAITING)
                .bookedAt(bookedAt)
                .createdAt(LocalDateTime.now())
                .createdBy(username)
                .restaurant(restaurant)
                .user(user)
                .build();

        Reservation savedReservation =  reservationRepository.save(reservation);

        return response(savedReservation, HttpStatus.CREATED.value(), SuccessCode.RESERVATION_SUCCESS);
    }

    private SuccessResponse<ReservationResponseDto> response(Reservation reservation, Integer status, SuccessCode successCode) {
        ReservationResponseDto data = ReservationResponseDto.builder()
                .id(reservation.getId())
                .partySize(reservation.getPartySize())
                .status(reservation.getStatus())
                .bookedAt(reservation.getBookedAt())
                .createdAt(reservation.getCreatedAt())
                .createdBy(reservation.getCreatedBy())
                .restaurantName(reservation.getRestaurant().getName())
                .build();

        return SuccessResponse.CREATED(data, status, successCode);
    }

    private SuccessResponse<List<ReservationResponseDto>> response(
            List<Reservation> reservations, Integer status, SuccessCode successCode) {

        List<ReservationResponseDto> data = reservations.stream()
                .map(reservation -> ReservationResponseDto.builder()
                        .id(reservation.getId())
                        .partySize(reservation.getPartySize())
                        .status(reservation.getStatus())
                        .bookedAt(reservation.getBookedAt())
                        .createdAt(reservation.getCreatedAt())
                        .createdBy(reservation.getCreatedBy())
                        .restaurantName(reservation.getRestaurant().getName())
                        .build())
                .collect(Collectors.toList());

        return SuccessResponse.OK(data, status, successCode);
    }
}
