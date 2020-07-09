package com.alexcode.eatgo.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;

    private String email;

    private String name;

    private Long level;

    private String role;

    private LocalDateTime createdAt;

    private String createdBy;
}
