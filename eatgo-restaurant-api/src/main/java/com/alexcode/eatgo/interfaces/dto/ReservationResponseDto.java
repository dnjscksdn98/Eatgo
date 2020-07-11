package com.alexcode.eatgo.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {

    private Long id;

    private Integer partySize;

    private String status;

    private LocalDateTime bookedAt;

    private LocalDateTime createdAt;

    private String createdBy;

    @JsonInclude(NON_NULL)
    private LocalDateTime updatedAt;

    @JsonInclude(NON_NULL)
    private String updatedBy;

    private Long userId;
}
