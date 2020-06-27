package com.alexcode.eatgo.application.exceptions;

import com.alexcode.eatgo.exceptions.ErrorCode;
import com.alexcode.eatgo.exceptions.InvalidValueException;

public class WrongPasswordException extends InvalidValueException {

  public WrongPasswordException() {
    super(ErrorCode.INVALID_LOGIN_VALUE);
  }
}
