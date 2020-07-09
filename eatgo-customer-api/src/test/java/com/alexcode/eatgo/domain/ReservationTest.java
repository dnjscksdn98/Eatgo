package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.EatgoCustomerApiApplicationTests;
import com.alexcode.eatgo.domain.models.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReservationTest extends EatgoCustomerApiApplicationTests {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void create() {
        Reservation reservation = Reservation.builder()
                .partySize(2)
                .status("WAITING")
                .bookedAt(LocalDateTime.now().plusDays(2))
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                //.userId(1L)
                //.restaurantId(1L)
                .build();

        Reservation saved = reservationRepository.save(reservation);

        assertNotNull(saved);
//        assertEquals(saved.getUserId(), 1L);
//        assertEquals(saved.getRestaurantId(), 1L);
    }
}
