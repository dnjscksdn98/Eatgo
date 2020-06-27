package com.alexcode.eatgo.interfaces.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class SessionRequestDto {

  @Email
  @NotEmpty
  @NotBlank
  private String email;

  @NotEmpty
  @NotBlank
  private String password;

}
