package com.alexcode.eatgo.domain.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long restaurantId;

    private String name;

    @NotEmpty
    @Setter
    private String date;

    @NotEmpty
    @Setter
    private String time;

    @NotNull
    @Setter
    @Min(1)
    private Integer partySize;
}
