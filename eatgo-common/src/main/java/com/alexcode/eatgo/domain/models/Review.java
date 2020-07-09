package com.alexcode.eatgo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "restaurant"})
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double score;

  private String content;

  private LocalDateTime createdAt;

  private String createdBy;

  private LocalDateTime updatedAt;

  private String updatedBy;

  @Setter
  @ManyToOne
  @JsonIgnore
  private Restaurant restaurant;

  @ManyToOne
  @JsonIgnore
  private User user;

}
