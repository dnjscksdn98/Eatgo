package com.alexcode.eatgo.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantUpdateDto {

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String address;
}
