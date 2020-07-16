package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.ReservationService;
import com.alexcode.eatgo.domain.repository.RestaurantRepository;
import com.alexcode.eatgo.domain.repository.UserRepository;
import com.alexcode.eatgo.domain.models.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJ0ZXN0ZXIifQ.lFpZkkerjEjRKo51nzjgMhTxJeV0aEFgPul9G8xQvU8";
        Long restaurantId = 1L;
        Long userId = 1004L;
        String name = "tester";
        String date = "2020-12-12";
        String time = "20:00";
        Integer partySize = 5;

        String str = date + " " + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime bookedAt = LocalDateTime.parse(str, formatter);

        Reservation mockReservation = Reservation.builder()
                .id(1L)
                .restaurant(restaurantRepository.getOne(restaurantId))
                .user(userRepository.getOne(userId))
                .createdBy(name)
                .bookedAt(bookedAt)
                .partySize(partySize)
                .build();

//        given(reservationService.addReservation(any(), any(), any(), any(), any(), any())).willReturn(mockReservation);

        mvc.perform(post("/restaurants/1/reservations")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"date\":\"2020-12-12\",\"time\":\"20:00\",\"partySize\":5}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1/reservations/1"));

//        verify(reservationService).addReservation(
//                eq(restaurantId), eq(userId), eq(name), eq(date), eq(time), eq(partySize));
    }

    @Test
    public void createWithInvalidResource() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJ0ZXN0ZXIifQ.lFpZkkerjEjRKo51nzjgMhTxJeV0aEFgPul9G8xQvU8";

        mvc.perform(post("/restaurants/1/reservations")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"date\":\"2020-12-12\",\"time\":\"20:00\",\"partySize\":0}"))
                .andExpect(status().isBadRequest());

//        verify(reservationService, never()).addReservation(any(), any(), any(), any(), any(), any());
    }
}