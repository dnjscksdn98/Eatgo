package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.ReservationRepository;
import com.alexcode.eatgo.domain.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ReservationServiceTest {

    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void addReservation() {
        Long restaurantId = 1L;
        Long userId = 1004L;
        String name = "tester";
        String date = "2020-12-12";
        String time = "20:00";
        Integer partySize = 5;

        given(reservationRepository.save(any(Reservation.class)))
                .will(invocation -> {
                    Reservation reservation = invocation.getArgument(0);
                    return reservation;
                });

        Reservation reservation = reservationService.addReservation(
                restaurantId, userId, name, date, time, partySize
        );

        assertEquals(reservation.getName(), name);
        verify(reservationRepository).save(any(Reservation.class));
    }

}