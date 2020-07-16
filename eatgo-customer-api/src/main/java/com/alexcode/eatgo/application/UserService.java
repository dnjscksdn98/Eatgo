package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.repository.UserRepository;
import com.alexcode.eatgo.domain.exceptions.EmailDuplicationException;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.network.SuccessCode;
import com.alexcode.eatgo.network.SuccessResponse;
import com.alexcode.eatgo.exceptions.PasswordMismatchException;
import com.alexcode.eatgo.interfaces.dto.UserRequestDto;
import com.alexcode.eatgo.interfaces.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.alexcode.eatgo.security.ApplicationUserRole.CUSTOMER;
import static org.springframework.http.HttpStatus.CREATED;

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

  public SuccessResponse<UserResponseDto> create(UserRequestDto request) {
    String email = request.getEmail();
    String name = request.getName();
    String password = request.getPassword();
    String confirmPassword = request.getConfirmPassword();

    if(userRepository.findByEmail(email).isPresent()) {
      throw new EmailDuplicationException(email);
    }

    if(!isPasswordConfirmed(password, confirmPassword)) {
      throw new PasswordMismatchException();
    }

    String encodedPassword = passwordEncoder.encode(password);

    User user = User.builder()
            .email(email)
            .name(name)
            .password(encodedPassword)
            .level(1L)
            .role(CUSTOMER)
            .createdAt(LocalDateTime.now())
            .createdBy("ADMISSION")
            .build();

    User savedUser = userRepository.save(user);

    return response(savedUser, CREATED.value(), SuccessCode.USER_ADMISSION_SUCCESS);
  }

  private boolean isPasswordConfirmed(String password, String confirmPassword) {
    if(password.equals(confirmPassword)) {
      return true;
    }
    return false;
  }

  private SuccessResponse<UserResponseDto> response(User user, Integer status, SuccessCode successCode) {
    UserResponseDto userResponseDto = UserResponseDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .level(user.getLevel())
            .role(user.getRole().name())
            .createdAt(user.getCreatedAt())
            .createdBy(user.getCreatedBy())
            .build();

    return SuccessResponse.CREATED(userResponseDto, status, successCode);
  }

}
