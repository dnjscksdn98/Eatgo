package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAll();

  Optional<User> findById(Long id);

  Optional<User> findByEmail(String email);

  User save(User user);

}
