package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.EmailNotExistsException;
import com.alexcode.eatgo.application.exceptions.WrongPasswordException;
import com.alexcode.eatgo.domain.UserRepository;
import com.alexcode.eatgo.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

  public User authenticate(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new EmailNotExistsException(email));

    if(!passwordEncoder.matches(password, user.getPassword())) {
      throw new WrongPasswordException();
    }

    return user;
  }

}
