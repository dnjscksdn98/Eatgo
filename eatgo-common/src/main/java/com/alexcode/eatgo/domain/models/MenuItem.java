package com.alexcode.eatgo.domain.models;

import com.alexcode.eatgo.domain.status.MenuItemStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.alexcode.eatgo.security.ApplicationUserRole.ADMIN;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

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

  @Enumerated(value = EnumType.STRING)
  private MenuItemStatus status;

  private LocalDateTime createdAt;

  private String createdBy;

  @JsonInclude(NON_NULL)
  private LocalDateTime updatedAt;

  @JsonInclude(NON_NULL)
  private String updatedBy;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private Restaurant restaurant;

  public void updateMenuItem(String name, String content, Long price, MenuItemStatus status) {
    this.name = name;
    this.content = content;
    this.price = price;
    this.status = status;
    this.updatedAt = LocalDateTime.now();
    this.updatedBy = ADMIN.name();
  }

}
