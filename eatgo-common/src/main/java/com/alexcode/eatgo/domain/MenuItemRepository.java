package com.alexcode.eatgo.domain;

import java.util.List;

import com.alexcode.eatgo.domain.models.MenuItem;
import org.springframework.data.repository.CrudRepository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

  List<MenuItem> findAllByRestaurantId(Long restaurantId);

  void deleteById(Long id);
}
