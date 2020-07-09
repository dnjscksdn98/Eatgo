package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.domain.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation save(Reservation reservation);

    List<Reservation> findAllByRestaurantId(Long restaurantId);
}
