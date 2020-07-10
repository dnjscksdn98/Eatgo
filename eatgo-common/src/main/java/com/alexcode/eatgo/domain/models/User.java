package com.alexcode.eatgo.domain.models;

import com.alexcode.eatgo.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static com.alexcode.eatgo.security.ApplicationUserRole.ADMIN;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"restaurant", "reviews", "reservations"})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String name;

  private String password;

  private Long level;

  private ApplicationUserRole role;

  private LocalDateTime createdAt;
  
  private String createdBy;

  private LocalDateTime updatedAt;

  private String updatedBy;

  private LocalDateTime lastLoginAt;

  @OneToOne
  @JsonInclude(Include.NON_NULL)
  private Restaurant restaurant;

  @JsonInclude(Include.NON_EMPTY)
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<Review> reviews;

  @JsonInclude(Include.NON_EMPTY)
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<Reservation> reservations;

  public boolean isAdmin() {
    return level == 100L;
  }

  public boolean isRestaurantOwner() {
    if(this.level == 50L) {
      return true;
    }
    return false;
  }

  public boolean isActive() {
    return level > 0;
  }

  public void deactivate() {
    level = 0L;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.level = 50L;
    this.restaurant = restaurant;
  }

  public void updateUser(String email, String name, Long level, ApplicationUserRole role) {
    this.email = email;
    this.name = name;
    this.level = level;
    this.role = role;
    this.updatedAt = LocalDateTime.now();
    this.updatedBy = ADMIN.name();
  }

}
