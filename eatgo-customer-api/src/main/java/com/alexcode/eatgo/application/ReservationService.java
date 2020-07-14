package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.ReservationRepository;
import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.UserRepository;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.exceptions.UserNotFoundException;
import com.alexcode.eatgo.domain.models.Reservation;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.network.SuccessCode;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.ReservationRequestDto;
import com.alexcode.eatgo.interfaces.dto.ReservationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
                .status("WAITING")
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
}
