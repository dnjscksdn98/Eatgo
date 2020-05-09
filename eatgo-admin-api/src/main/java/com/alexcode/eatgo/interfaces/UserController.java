package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

  // 1. User List
  // 2. User create
  // 3. User update
  // 4. User delete -> level: 0 => 사용 일시 중지
  // (1: customer, 2: restaurant owner, 3: admin)

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<User> list() {
    return userService.getUsers();
  }

  @PostMapping("/users")
  public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
    User user = userService.addUser(resource.getEmail(), resource.getName());
    URI location = new URI("/users/" + user.getId());

    return ResponseEntity.created(location).body("{}");
  }

  @PatchMapping("/users/{userId}")
  public String update(
      @PathVariable("userId") Long userId,
      @RequestBody User resource) {

    String email = resource.getEmail();
    String name = resource.getName();
    Long level = resource.getLevel();

    userService.updateUser(userId, email, name, level);
    return "{}";
  }
}
