package com.alexcode.eatgo.domain.exceptions;

import com.alexcode.eatgo.exceptions.EntityNotFoundException;
import com.alexcode.eatgo.exceptions.ErrorCode;

public class RestaurantNotFoundException extends EntityNotFoundException {

  public RestaurantNotFoundException(Long id) {
    super("Could not find restaurant with id : " + id, ErrorCode.ENTITY_NOT_FOUND);
  }
}
