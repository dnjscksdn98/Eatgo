package com.alexcode.eatgo.interfaces.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequestDto {

  @Email
  @NotEmpty
  @NotBlank
  private String username;

  @NotEmpty
  @NotBlank
  private String password;

}
