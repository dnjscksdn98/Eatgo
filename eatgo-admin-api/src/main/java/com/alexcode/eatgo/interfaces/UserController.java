package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.interfaces.dto.UserRequestDto;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "management/api/v1/users")
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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('user:read')")
  public ResponseEntity<SuccessResponse<?>> list() {
    SuccessResponse<?> body = userService.list();
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('user:write')")
  public ResponseEntity<SuccessResponse<?>> create(@Valid @RequestBody UserRequestDto request) {
    SuccessResponse<?> body = userService.create(request);
    return new ResponseEntity<>(body, HttpStatus.CREATED);
  }

  @PatchMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('user:write')")
  public ResponseEntity<SuccessResponse<?>> update(
      @PathVariable("userId") Long userId,
      @Valid @RequestBody UserRequestDto request) {

    SuccessResponse<?> body = userService.update(request, userId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @DeleteMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('user:write')")
  public ResponseEntity<SuccessResponse<?>> deactivate(@PathVariable("userId") Long userId) {
    SuccessResponse<?> body = userService.deactivateUser(userId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }
}
