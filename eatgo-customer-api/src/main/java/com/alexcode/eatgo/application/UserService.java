package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.EmailExistsException;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

  public User registerUser(String email, String name, String password) {
    Optional<User> user = userRepository.findByEmail(email);
    if(user.isPresent()) throw new EmailExistsException(email);

    String encodedPassword = passwordEncoder.encode(password);

    return userRepository.save(
        User.builder()
          .email(email)
          .name(name)
          .password(encodedPassword)
          .level(1L)
          .build()
    );
  }

}
