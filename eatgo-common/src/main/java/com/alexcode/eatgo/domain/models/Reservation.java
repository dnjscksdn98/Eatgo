package com.alexcode.eatgo.domain.models;

import com.alexcode.eatgo.domain.status.ReservationStatus;
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

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime bookedAt;

    private LocalDateTime createdAt;

    private String createdBy;

    @JsonInclude(NON_NULL)
    private LocalDateTime updatedAt;

    @JsonInclude(NON_NULL)
    private String updatedBy;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public void update() {
        this.status = ReservationStatus.ACCEPTED;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = OWNER.name();
    }

    public void refuse() {
        this.status = ReservationStatus.REFUSED;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = OWNER.name();
    }

}
