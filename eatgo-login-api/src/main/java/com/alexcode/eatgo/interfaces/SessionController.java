package com.alexcode.eatgo.interfaces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SessionController {

  @PostMapping(path = "/login")
  public void login() {
  }

}
