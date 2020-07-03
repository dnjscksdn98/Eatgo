package com.alexcode.eatgo.interfaces.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {

  private String accessToken;

}
