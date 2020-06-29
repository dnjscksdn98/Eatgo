package com.alexcode.eatgo.application.exceptions;

import com.alexcode.eatgo.exceptions.ErrorCode;
import com.alexcode.eatgo.exceptions.InvalidValueException;

public class PasswordMismatchException extends InvalidValueException {

  public PasswordMismatchException() {
    super(ErrorCode.PASSWORD_MISMATCH);
  }
}
