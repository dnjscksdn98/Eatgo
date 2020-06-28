package com.alexcode.eatgo.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    @NotEmpty
    @Email
    private String email;

    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    private Long level;

}
