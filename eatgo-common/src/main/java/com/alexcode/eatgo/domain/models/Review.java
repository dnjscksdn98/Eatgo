package com.alexcode.eatgo.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

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

  @JsonInclude(NON_NULL)
  private LocalDateTime updatedAt;

  @JsonInclude(NON_NULL)
  private String updatedBy;

  @Setter
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private Restaurant restaurant;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

}
