package com.alexcode.eatgo.interfaces.handlers;

import com.alexcode.eatgo.application.exceptions.EmailNotExistsException;
import com.alexcode.eatgo.application.exceptions.WrongPasswordException;
import com.alexcode.eatgo.errors.ErrorCode;
import com.alexcode.eatgo.errors.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.logging.log4j.Logger;


@ControllerAdvice
public class SessionErrorAdvice {

  private static final Logger logger = LogManager.getLogger(SessionErrorAdvice.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
    logger.error("MethodArgumentNotValidException", e);
    final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(WrongPasswordException.class)
  protected String handleWrongPassword() {
    return "{}";
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(EmailNotExistsException.class)
  protected String handleEmailNotExists() {
    return "{}";
  }
}
