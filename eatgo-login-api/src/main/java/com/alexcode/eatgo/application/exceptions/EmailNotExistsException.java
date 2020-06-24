package com.alexcode.eatgo.application.exceptions;

public class EmailNotExistsException extends RuntimeException {

  public EmailNotExistsException(String email) {
    super("This email is not registered: " + email);
  }
}
