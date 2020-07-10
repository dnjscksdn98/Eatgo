package com.alexcode.eatgo.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreateRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @NotEmpty
    private String content;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long regionId;

    @NotNull
    private Long userId;

}
