package com.alexcode.eatgo.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

  @Id
  @GeneratedValue
  private Long id;

  @NotEmpty
  @NotBlank
  private String name;

  @NotEmpty
  @NotBlank
  private String address;

  @NotNull
  private Long categoryId;

  @Transient
  @JsonInclude(Include.NON_NULL)
  private List<MenuItem> menuItems;

  @Transient
  @JsonInclude(Include.NON_NULL)
  private List<Review> reviews;

  public String getInformation() {
    return name + " in " + address;
  }

  public void updateInformation(String name, String address) {
    this.name = name;
    this.address = address;
  }

  public void setMenuItems(List<MenuItem> menuItems) {
    this.menuItems = new ArrayList<>(menuItems);
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = new ArrayList<>(reviews);
  }
}
