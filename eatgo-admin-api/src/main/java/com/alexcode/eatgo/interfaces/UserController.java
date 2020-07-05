package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.interfaces.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("admin/api/v1/users")
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

  @GetMapping
  @PreAuthorize("hasAuthority('user:read')")
  public List<User> list() {
    return userService.getUsers();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('user:write')")
  public ResponseEntity<?> create(@Valid @RequestBody UserDto resource) throws URISyntaxException {
    String email = resource.getEmail();
    String name = resource.getName();
    Long level = resource.getLevel();

    User user = userService.addUser(email, name, level);
    URI location = new URI("/users/" + user.getId());

    return ResponseEntity.created(location).body("{}");
  }

  @PatchMapping(path = "/{userId}")
  @PreAuthorize("hasAuthority('user:write')")
  public String update(
      @PathVariable("userId") Long userId,
      @Valid @RequestBody UserDto resource) {

    String email = resource.getEmail();
    String name = resource.getName();
    Long level = resource.getLevel();

    userService.updateUser(userId, email, name, level);

    return "{}";
  }

  @DeleteMapping(path = "/{userId}")
  @PreAuthorize("hasAuthority('user:write')")
  public String deactivate(@PathVariable("userId") Long userId) {
    userService.deactivateUser(userId);

    return "{}";
  }
}
