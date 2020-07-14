package com.alexcode.eatgo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.alexcode.eatgo.security.ApplicationUserRole.OWNER;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

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

    private Integer partySize;

    private String status;

    private LocalDateTime bookedAt;

    private LocalDateTime createdAt;

    private String createdBy;

    @JsonInclude(NON_NULL)
    private LocalDateTime updatedAt;

    @JsonInclude(NON_NULL)
    private String updatedBy;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    public void update() {
        this.status = "ACCEPTED";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = OWNER.name();
    }

    public void cancel() {
        this.status = "CANCELED";
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = OWNER.name();
    }

}
