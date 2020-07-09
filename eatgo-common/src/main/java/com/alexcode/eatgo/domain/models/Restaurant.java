package com.alexcode.eatgo.domain.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

//  @Transient
//  @JsonInclude(Include.NON_NULL)
//  private List<MenuItem> menuItems;
//
//  @Transient
//  @JsonInclude(Include.NON_NULL)
//  private List<Review> reviews;

  public String getInformation() {
    return name + " in " + address;
  }

  public void updateInformation(String name, String address) {
    this.name = name;
    this.address = address;
  }

//  public void setMenuItems(List<MenuItem> menuItems) {
//    this.menuItems = new ArrayList<>(menuItems);
//  }
//
//  public void setReviews(List<Review> reviews) {
//    this.reviews = new ArrayList<>(reviews);
//  }
}
