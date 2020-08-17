package com.alexcode.eatgo.domain.models;

import com.alexcode.eatgo.domain.status.RestaurantStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static com.alexcode.eatgo.security.ApplicationUserRole.ADMIN;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "category", "region", "menuItems", "reviews", "reservations"})
public class Restaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String address;

  @Enumerated(value = EnumType.STRING)
  private RestaurantStatus status;

  private String content;

  private LocalDateTime createdAt;

  private String createdBy;

  private LocalDateTime updatedAt;

  private String updatedBy;

  @ManyToOne(fetch = FetchType.LAZY)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  private Region region;

  @OneToOne(fetch = FetchType.LAZY)
  private User user;

  @OneToMany(mappedBy = "restaurant")
  private List<MenuItem> menuItems;

  @OneToMany(mappedBy = "restaurant")
  private List<Review> reviews;

  @OneToMany(mappedBy = "restaurant")
  private List<Reservation> reservations;

  public void updateRestaurant(String name, String address, RestaurantStatus status, String content) {
    this.name = name;
    this.address = address;
    this.status = status;
    this.content = content;
    this.updatedAt = LocalDateTime.now();
    this.updatedBy = ADMIN.name();
  }

}
