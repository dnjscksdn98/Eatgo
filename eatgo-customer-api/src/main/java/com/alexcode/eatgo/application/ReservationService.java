package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.ReservationRepository;
import com.alexcode.eatgo.domain.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(
            Long restaurantId, Long userId, String name, String date, String time, Integer partySize) {
        Reservation reservation = Reservation.builder()
                .restaurantId(restaurantId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();
        return reservationRepository.save(reservation);
    }
}
