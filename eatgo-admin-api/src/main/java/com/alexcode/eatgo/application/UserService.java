package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.User;
import com.alexcode.eatgo.domain.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User addUser(String email, String name) {
    User user = User.builder()
        .email(email)
        .name(name)
        .build();
    return userRepository.save(user);
  }

}
