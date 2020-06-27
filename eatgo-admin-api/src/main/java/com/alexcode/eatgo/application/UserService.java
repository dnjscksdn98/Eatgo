package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.EmailDuplicationException;
import com.alexcode.eatgo.application.exceptions.UserNotFoundException;
import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.UserRepository;
import java.util.List;
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

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User addUser(String email, String name) {
    if(userRepository.findByEmail(email).isPresent()) {
      throw new EmailDuplicationException(email);
    }
    String temporaryPassword = passwordEncoder.encode("temporary");
    User user = User.builder()
            .email(email)
            .name(name)
            .password(temporaryPassword)
            .level(1L)
            .build();
    return userRepository.save(user);
  }

  public User updateUser(Long userId, String email, String name, Long level) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());

    user.setEmail(email);
    user.setName(name);
    user.setLevel(level);

    return user;
  }

  public User deactivateUser(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());
    user.deactivate();

    return user;
  }
}
