package com.alexcode.eatgo.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {

    @NotEmpty
    @NotBlank
    private String date;

    @NotEmpty
    @NotBlank
    private String time;

    @NotNull
    private Integer partySize;
}
