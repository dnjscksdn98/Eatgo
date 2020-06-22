package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.models.User;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/users")
  public ResponseEntity<?> register(@RequestBody User resource) throws URISyntaxException {
    String email = resource.getEmail();
    String name = resource.getName();
    String password = resource.getPassword();

    User user = userService.registerUser(email, name, password);

    URI location = new URI("/users/" + user.getId());

    return ResponseEntity.created(location).body("{}");
  }
}
