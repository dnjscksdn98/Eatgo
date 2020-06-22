package com.alexcode.eatgo.domain.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

  @Id
  @Setter
  @GeneratedValue
  private Long id;

  @NotNull
  private Long categoryId;

  @NotEmpty
  private String name;

  @NotEmpty
  private String address;

  @Transient
  @JsonInclude(Include.NON_NULL)
  private List<MenuItem> menuItems;

  @Transient
  @JsonInclude(Include.NON_NULL)
  private List<Review> reviews;

  public void updateInformation(String name, String address) {
    this.name = name;
    this.address = address;
  }

  public String getInformation() {
    return name + " in " + address;
  }

  public void setMenuItems(List<MenuItem> menuItems) {
    this.menuItems = new ArrayList<>(menuItems);
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = new ArrayList<>(reviews);
  }
}
