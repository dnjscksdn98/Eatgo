package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.UserNotFoundException;
import com.alexcode.eatgo.domain.UserRepository;
import com.alexcode.eatgo.domain.exceptions.EmailDuplicationException;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.UserRequestDto;
import com.alexcode.eatgo.interfaces.dto.UserResponseDto;
import com.alexcode.eatgo.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
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

    return response(users, 200);
  }

  public SuccessResponse<UserResponseDto> create(UserRequestDto request) {
    String email = request.getEmail();
    String name = request.getName();
    Long level = request.getLevel();

    if(userRepository.findByEmail(email).isPresent()) {
      throw new EmailDuplicationException(email);
    }

    String temporaryPassword = passwordEncoder.encode("temporary");

    ApplicationUserRole role;
    if(level == 1L) {
      role = CUSTOMER;
    }
    else if(level == 50L) {
      role = OWNER;
    }
    else {
      role = ADMIN;
    }

    User user = User.builder()
            .email(email)
            .name(name)
            .password(temporaryPassword)
            .level(level)
            .role(role)
            .createdAt(LocalDateTime.now())
            .createdBy(ADMIN.name())
            .build();

    User savedUser = userRepository.save(user);

    return response(savedUser, 201);
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

    ApplicationUserRole role;
    if(level == 1L) {
      role = CUSTOMER;
    }
    else if(level == 50L) {
      role = OWNER;
    }
    else {
      role = ADMIN;
    }

    user.updateUser(email, name, level, role);

    return response(user, 200);
  }

  public void deactivateUser(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

    user.deactivate();
  }

  private SuccessResponse<UserResponseDto> response(User user, Integer status) {
    UserResponseDto data = UserResponseDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .level(user.getLevel())
            .role(user.getRole().name())
            .createdAt(user.getCreatedAt())
            .createdBy(user.getCreatedBy())
            .updatedAt(user.getUpdatedAt())
            .updatedBy(user.getUpdatedBy())
            .build();

    return SuccessResponse.OK(data, status);
  }

  private SuccessResponse<List<UserResponseDto>> response(List<User> users, Integer status) {
    List<UserResponseDto> data = users.stream()
            .map(user -> UserResponseDto.builder()
                      .id(user.getId())
                      .email(user.getEmail())
                      .name(user.getName())
                      .level(user.getLevel())
                      .role(user.getRole().name())
                      .createdAt(user.getCreatedAt())
                      .createdBy(user.getCreatedBy())
                      .updatedAt(user.getUpdatedAt())
                      .updatedBy(user.getUpdatedBy())
                      .lastLoginAt(user.getLastLoginAt())
                      .build()
            )
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status);
  }
}
