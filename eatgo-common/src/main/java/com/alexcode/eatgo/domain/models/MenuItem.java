package com.alexcode.eatgo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.alexcode.eatgo.security.ApplicationUserRole.ADMIN;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"restaurant"})
public class MenuItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  private String name;

  private String content;

  private Long price;

  private String status;

  private LocalDateTime createdAt;

  private String createdBy;

  private LocalDateTime updatedAt;

  private String updatedBy;

  @ManyToOne
  @JsonIgnore
  private Restaurant restaurant;

  public void updateMenuItem(String name, String content, Long price, String status) {
    this.name = name;
    this.content = content;
    this.price = price;
    this.status = status;
    this.updatedAt = LocalDateTime.now();
    this.updatedBy = ADMIN.name();
  }

}
