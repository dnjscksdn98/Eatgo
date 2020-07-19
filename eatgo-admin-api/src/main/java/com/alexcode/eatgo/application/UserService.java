package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.exceptions.EmailDuplicationException;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.repository.UserRepository;
import com.alexcode.eatgo.domain.status.UserStatus;
import com.alexcode.eatgo.exceptions.UserNotFoundException;
import com.alexcode.eatgo.interfaces.dto.UserRequestDto;
import com.alexcode.eatgo.interfaces.dto.UserResponseDto;
import com.alexcode.eatgo.network.SuccessCode;
import com.alexcode.eatgo.network.SuccessResponse;
import com.alexcode.eatgo.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.alexcode.eatgo.security.ApplicationUserRole.*;

@Service
@Transactional
public class UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public SuccessResponse<List<UserResponseDto>> list() {
    List<User> users = userRepository.findAll();

    return response(users, HttpStatus.OK.value(), SuccessCode.OK);
  }

  public SuccessResponse<UserResponseDto> create(UserRequestDto request) {
    String email = request.getEmail();
    String name = request.getName();
    Long level = request.getLevel();

    if(userRepository.findByEmail(email).isPresent()) {
      throw new EmailDuplicationException(email);
    }

    String temporaryPassword = passwordEncoder.encode("password");

    ApplicationUserRole role = getRoleByLevel(level);

    User user = User.builder()
            .email(email)
            .name(name)
            .password(temporaryPassword)
            .level(level)
            .status(UserStatus.REGISTERED)
            .role(role)
            .createdAt(LocalDateTime.now())
            .createdBy(ADMIN.name())
            .build();

    User savedUser = userRepository.save(user);

    return response(savedUser, HttpStatus.CREATED.value(), SuccessCode.USER_CREATION_SUCCESS);
  }

  public SuccessResponse<UserResponseDto> update(UserRequestDto request, Long userId) {
    String email = request.getEmail();
    String name = request.getName();
    Long level = request.getLevel();

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

    if(userRepository.findByEmail(email).isPresent()) {
      throw new EmailDuplicationException(email);
    }

    ApplicationUserRole role = getRoleByLevel(level);

    user.updateUser(email, name, level, role);

    return response(user, HttpStatus.OK.value(), SuccessCode.USER_UPDATE_SUCCESS);
  }

  public SuccessResponse<?> deactivateUser(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

    user.deactivate();

    return SuccessResponse.OK(HttpStatus.OK.value(), SuccessCode.USER_DEACTIVATION_SUCCESS);
  }

  private ApplicationUserRole getRoleByLevel(Long level) {
    if(level == 100L) return ADMIN;
    if(level == 50L) return OWNER;
    return CUSTOMER;
  }

  private SuccessResponse<UserResponseDto> response(User user, Integer status, SuccessCode successCode) {
    UserResponseDto data = UserResponseDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .level(user.getLevel())
            .status(user.getStatus())
            .role(user.getRole().name())
            .createdAt(user.getCreatedAt())
            .createdBy(user.getCreatedBy())
            .updatedAt(user.getUpdatedAt())
            .updatedBy(user.getUpdatedBy())
            .build();

    return SuccessResponse.OK(data, status, successCode);
  }

  private SuccessResponse<List<UserResponseDto>> response(List<User> users, Integer status, SuccessCode successCode) {
    List<UserResponseDto> data = users.stream()
            .map(user -> UserResponseDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .level(user.getLevel())
                    .status(user.getStatus())
                    .role(user.getRole().name())
                    .createdAt(user.getCreatedAt())
                    .createdBy(user.getCreatedBy())
                    .updatedAt(user.getUpdatedAt())
                    .updatedBy(user.getUpdatedBy())
                    .lastLoginAt(user.getLastLoginAt())
                    .build()
            )
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status, successCode);
  }
}
