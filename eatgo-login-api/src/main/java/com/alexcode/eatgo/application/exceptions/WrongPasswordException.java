package com.alexcode.eatgo.application.exceptions;

public class WrongPasswordException extends RuntimeException {

  public WrongPasswordException() {
    super("Password is wrong");
  }
}
