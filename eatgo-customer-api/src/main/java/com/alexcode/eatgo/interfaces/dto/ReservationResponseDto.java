package com.alexcode.eatgo.interfaces.dto;

import com.alexcode.eatgo.domain.status.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {

    private Long id;

    private Integer partySize;

    private ReservationStatus status;

    private LocalDateTime bookedAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private String restaurantName;

}
