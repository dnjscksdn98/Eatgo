package com.alexcode.eatgo.interfaces.dto;

import com.alexcode.eatgo.domain.models.MenuItem;
import com.alexcode.eatgo.domain.models.Reservation;
import com.alexcode.eatgo.domain.models.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDto {

    private Long id;

    private String name;

    private String address;

    private String status;

    private String content;

    private LocalDateTime createdAt;

    private String createdBy;

    @JsonInclude(NON_NULL)
    private LocalDateTime updatedAt;

    @JsonInclude(NON_NULL)
    private String updatedBy;

    private String categoryName;

    private String regionName;

    private String owner;

    @JsonInclude(NON_EMPTY)
    private List<MenuItem> menuItems;

    @JsonInclude(NON_EMPTY)
    private List<Review> reviews;

    @JsonInclude(NON_EMPTY)
    private List<Reservation> reservations;

}
