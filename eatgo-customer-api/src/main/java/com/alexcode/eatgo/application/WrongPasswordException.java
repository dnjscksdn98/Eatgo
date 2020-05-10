package com.alexcode.eatgo.application;

public class WrongPasswordException extends RuntimeException {

  public WrongPasswordException() {
    super("Password is wrong");
  }
}
