package com.alexcode.eatgo.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

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

    @JsonInclude(NON_NULL)
    private LocalDateTime updatedAt;

    @JsonInclude(NON_NULL)
    private String updatedBy;

    @JsonInclude(NON_NULL)
    private LocalDateTime lastLoginAt;

}
