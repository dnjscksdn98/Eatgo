package com.alexcode.eatgo.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;
}
