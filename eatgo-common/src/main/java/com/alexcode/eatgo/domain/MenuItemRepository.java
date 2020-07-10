package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.domain.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

  List<MenuItem> findAllByRestaurantId(Long restaurantId);

  Optional<MenuItem> findById(Long id);

  void deleteById(Long id);

  MenuItem save(MenuItem menuItem);
}
