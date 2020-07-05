package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.interfaces.dto.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<?> register(
          @Valid @RequestBody UserRegisterDto resource) throws URISyntaxException {

    String email = resource.getEmail();
    String name = resource.getName();
    String password = resource.getPassword();
    String confirmPassword = resource.getConfirmPassword();

    User user = userService.registerUser(email, name, password, confirmPassword);
    URI location = new URI("/users/" + user.getId());

    return ResponseEntity.created(location).body("{}");
  }

}
