package com.alexcode.eatgo.interfaces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/login")
public class SessionController {

  @PostMapping
  public void login() {
  }

}
