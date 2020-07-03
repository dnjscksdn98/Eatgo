package com.alexcode.eatgo.domain.models;

import com.alexcode.eatgo.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
  @Email
  private String email;

  @Setter
  @NotEmpty
  @NotBlank
  private String name;

  @NotEmpty
  @NotBlank
  private String password;

  @JsonInclude(Include.NON_NULL)
  private Long restaurantId;

  @Setter
  @NotNull
  private Long level;

  private ApplicationUserRole role;

  public boolean isAdmin() {
    return level == 100L;
  }

  public boolean isRestaurantOwner() {
    if(this.level == 50L) {
      return true;
    }
    return false;
  }

  public boolean isActive() {
    return level > 0;
  }

  public void setRestaurantId(Long restaurantId) {
    this.level = 50L;
    this.restaurantId = restaurantId;
  }

  public void deactivate() {
    level = 0L;
  }

}
