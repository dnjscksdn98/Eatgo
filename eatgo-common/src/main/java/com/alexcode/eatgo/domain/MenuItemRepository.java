package com.alexcode.eatgo.domain;

import java.util.List;
import java.util.Optional;

import com.alexcode.eatgo.domain.models.MenuItem;
import org.springframework.data.repository.CrudRepository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

  List<MenuItem> findAllByRestaurantId(Long restaurantId);

  Optional<MenuItem> findById(Long id);

  void deleteById(Long id);
}
