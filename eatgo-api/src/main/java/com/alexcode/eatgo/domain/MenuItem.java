package com.alexcode.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Service
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

  @Id
  @GeneratedValue
  private Long id;

  private Long restaurantId;

  private String name;
}
