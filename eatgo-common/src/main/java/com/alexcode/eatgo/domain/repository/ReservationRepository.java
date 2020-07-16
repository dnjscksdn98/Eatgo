package com.alexcode.eatgo.domain.repository;

import com.alexcode.eatgo.domain.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation save(Reservation reservation);

    List<Reservation> findAllByRestaurantId(Long restaurantId);

    Optional<Reservation> findByIdAndRestaurantId(Long id, Long restaurantId);
}
