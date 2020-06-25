package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.domain.models.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    Reservation save(Reservation reservation);
}
