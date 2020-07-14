package com.alexcode.eatgo.exceptions;

public class PasswordMismatchException extends InvalidValueException {

  public PasswordMismatchException() {
    super(ErrorCode.PASSWORD_MISMATCH);
  }
}
