package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.models.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.alexcode.eatgo.interfaces.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController {

  /**
   * list(): 사용자 리스트 반환
   * create(): 사용자 생성
   * update(): 사용자 정보 변경
   * deactivate(): 사용자 비활성화
   *
   * level:
   *      1 -> 고객
   *      50 -> 가게 주인
   *      100 -> 관리자
   *
   */

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<User> list() {
    return userService.getUsers();
  }

  @PostMapping("/users")
  public ResponseEntity<?> create(@Valid @RequestBody UserDto resource) throws URISyntaxException {
    String email = resource.getEmail();
    String name = resource.getName();
    User user = userService.addUser(email, name);
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

  @DeleteMapping("/users/{userId}")
  public String deactivate(@PathVariable("userId") Long userId) {
    userService.deactivateUser(userId);
    return "{}";
  }
}
