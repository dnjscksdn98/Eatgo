package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.UserRequestDto;
import com.alexcode.eatgo.interfaces.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/users")
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
  public SuccessResponse<List<UserResponseDto>> list() {
    return userService.list();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('user:write')")
  public SuccessResponse<UserResponseDto> create(
          @Valid @RequestBody UserRequestDto request) {

    return userService.create(request);
  }

  @PatchMapping(path = "/{userId}")
  @PreAuthorize("hasAuthority('user:write')")
  public SuccessResponse<UserResponseDto> update(
      @PathVariable("userId") Long userId,
      @Valid @RequestBody UserRequestDto request) {

    return userService.update(request, userId);
  }

  @DeleteMapping(path = "/{userId}")
  @PreAuthorize("hasAuthority('user:write')")
  public String deactivate(@PathVariable("userId") Long userId) {
    userService.deactivateUser(userId);

    return "{}";
  }
}
