package com.alexcode.eatgo.interfaces.dto;

import com.alexcode.eatgo.domain.models.MenuItem;
import com.alexcode.eatgo.domain.models.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"menuItems", "reviews"})
public class RestaurantResponseDto {

    private Long id;

    private String name;

    private String address;

    private String content;

    private String categoryName;

    private String regionName;

    private String userName;

    @JsonInclude(NON_EMPTY)
    private List<MenuItem> menuItems;

    @JsonInclude(NON_EMPTY)
    private List<Review> reviews;

}
