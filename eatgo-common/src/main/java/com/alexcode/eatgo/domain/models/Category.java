package com.alexcode.eatgo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ToString(exclude = {"restaurants"})
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private LocalDateTime createdAt;

  private String createdBy;

  private LocalDateTime updatedAt;

  private String updatedBy;

  @JsonIgnore
  @OneToMany(mappedBy = "category")
  private List<Restaurant> restaurants;

  public void updateCategory(String name) {
    this.name = name;
    this.updatedAt = LocalDateTime.now();
    this.updatedBy = ADMIN.name();
  }

}
