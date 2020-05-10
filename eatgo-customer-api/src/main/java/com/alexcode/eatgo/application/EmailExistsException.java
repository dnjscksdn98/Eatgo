package com.alexcode.eatgo.application;

public class EmailExistsException extends RuntimeException {

  public EmailExistsException(String email) {
    super("This Email is already registered: " + email);
  }
}
