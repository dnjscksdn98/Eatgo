package com.alexcode.eatgo.interfaces.dto;

import com.alexcode.eatgo.domain.status.UserStatus;
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

    private UserStatus status;

    private Long level;

    private String role;

    private LocalDateTime createdAt;

    private String createdBy;
}
