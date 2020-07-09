package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.ReservationRepository;
import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.UserRepository;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.exceptions.UserNotFoundException;
import com.alexcode.eatgo.domain.models.Reservation;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.ReservationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
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
            Long restaurantId, Long userId, String username, String date, String time, Integer partySize) {

        String str = date + " " + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime bookedAt = LocalDateTime.parse(str, formatter);

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Reservation reservation = Reservation.builder()
                .partySize(partySize)
                .status("WAITING")
                .bookedAt(bookedAt)
                .createdAt(LocalDateTime.now())
                .createdBy(username)
                .restaurant(restaurant)
                .user(user)
                .build();

        Reservation savedReservation =  reservationRepository.save(reservation);

        return response(savedReservation, 201);
    }

    private SuccessResponse<ReservationResponseDto> response(Reservation reservation, Integer status) {
        ReservationResponseDto data = ReservationResponseDto.builder()
                .id(reservation.getId())
                .partySize(reservation.getPartySize())
                .status(reservation.getStatus())
                .bookedAt(reservation.getBookedAt())
                .createdAt(reservation.getCreatedAt())
                .createdBy(reservation.getCreatedBy())
                .restaurantId(reservation.getRestaurant().getId())
                .userId(reservation.getUser().getId())
                .build();

        return SuccessResponse.OK(data, status);
    }
}
