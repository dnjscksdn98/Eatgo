package com.alexcode.eatgo.application;

public class EmailNotExistsException extends RuntimeException {

  public EmailNotExistsException(String email) {
    super("This email is not registered: " + email);
  }
}
