package com.alexcode.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

  @Id
  @GeneratedValue
  private Long id;

  @Setter
  private Long restaurantId;

  @NotEmpty
  private String name;

  @Min(0)
  @Max(5)
  @NotNull
  private Integer score;

  @NotEmpty
  private String description;

}
