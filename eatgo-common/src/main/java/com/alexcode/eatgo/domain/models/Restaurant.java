package com.alexcode.eatgo.domain.models;

import com.alexcode.eatgo.security.ApplicationUserRole;
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

  private String status;

  private String content;

  private LocalDateTime createdAt;

  private String createdBy;

  private LocalDateTime updatedAt;

  private String updatedBy;

  @ManyToOne
  private Category category;

  @ManyToOne
  private Region region;

  @OneToOne
  private User user;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
  private List<MenuItem> menuItems;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
  private List<Review> reviews;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
  private List<Reservation> reservations;

  public void updateRestaurant(String name, String address, String status, String content) {
    this.name = name;
    this.address = address;
    this.status = status;
    this.content = content;
    this.updatedAt = LocalDateTime.now();
    this.updatedBy = ADMIN.name();
  }

}
