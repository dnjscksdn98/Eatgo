package com.alexcode.eatgo.interfaces.dto;

import com.alexcode.eatgo.domain.status.MenuItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemUpdateRequestDto {

    @NotNull
    @NotBlank
    private Long id;

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String content;

    @NotNull
    @NotBlank
    private Long price;

    @NotEmpty
    @NotBlank
    private MenuItemStatus status;

    private boolean destroy;
}
