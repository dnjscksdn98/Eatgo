package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.PasswordMismatchException;
import com.alexcode.eatgo.domain.UserRepository;
import com.alexcode.eatgo.domain.exceptions.EmailDuplicationException;
import com.alexcode.eatgo.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.alexcode.eatgo.security.ApplicationUserRole.CUSTOMER;

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

  public User registerUser(String email, String name, String password, String confirmPassword) {
    if(userRepository.findByEmail(email).isPresent()) {
      throw new EmailDuplicationException(email);
    }

    if(!isPasswordConfirmed(password, confirmPassword)) {
      throw new PasswordMismatchException();
    }

    String encodedPassword = passwordEncoder.encode(password);

    return userRepository.save(
        User.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .role(CUSTOMER)
                .level(1L)
                .build()
    );
  }

  private boolean isPasswordConfirmed(String password, String confirmPassword) {
    if(password.equals(confirmPassword)) {
      return true;
    }
    return false;
  }

}
