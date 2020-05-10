package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.User;
import com.alexcode.eatgo.domain.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User registerUser(String email, String name, String password) {
    Optional<User> existed = userRepository.findByEmail(email);
    if(existed.isPresent()) throw new EmailExistsException(email);

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(password);

    User user = User.builder()
        .email(email)
        .name(name)
        .password(encodedPassword)
        .level(1L)
        .build();

    return userRepository.save(user);
  }

}
