package com.alexcode.eatgo.domain.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "restaurant"})
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private Integer partySize;

    private String status;

    private LocalDateTime bookedAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

}
