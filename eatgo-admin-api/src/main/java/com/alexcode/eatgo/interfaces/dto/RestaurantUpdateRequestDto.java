package com.alexcode.eatgo.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantUpdateRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @NotEmpty
    private String status;

    @NotEmpty
    private String content;

}
