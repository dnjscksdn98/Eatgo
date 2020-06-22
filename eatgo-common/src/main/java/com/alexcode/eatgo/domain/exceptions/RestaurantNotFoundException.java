package com.alexcode.eatgo.domain.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

  public RestaurantNotFoundException(Long id) {
    super("Could not find restaurant with id : " + id);
  }
}
