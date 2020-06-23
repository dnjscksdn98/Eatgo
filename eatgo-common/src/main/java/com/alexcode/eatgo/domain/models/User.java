package com.alexcode.eatgo.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @Setter
  @NotEmpty
  private String email;

  @Setter
  @NotEmpty
  private String name;

  @NotEmpty
  private String password;

  @Setter
  @NotNull
  private Long level;

  public boolean isAdmin() {
    return level >= 3;
  }

  public boolean isActive() {
    return level > 0;
  }

  public void deactivate() {
    level = 0L;
  }

}
