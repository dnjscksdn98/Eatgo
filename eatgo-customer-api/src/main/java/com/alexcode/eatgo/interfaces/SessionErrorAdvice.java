package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.exceptions.EmailExistsException;
import com.alexcode.eatgo.application.exceptions.EmailNotExistsException;
import com.alexcode.eatgo.application.exceptions.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SessionErrorAdvice {

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(WrongPasswordException.class)
  public String handleWrongPassword() {
    return "{}";
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(EmailExistsException.class)
  public String handleEmailExists() {
    return "{}";
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(EmailNotExistsException.class)
  public String handleEmailNotExists() {
    return "{}";
  }
}
