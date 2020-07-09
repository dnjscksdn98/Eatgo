package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.UserRequestDto;
import com.alexcode.eatgo.interfaces.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public SuccessResponse<UserResponseDto> create(@Valid @RequestBody UserRequestDto request) {

    return userService.create(request);
  }

}
