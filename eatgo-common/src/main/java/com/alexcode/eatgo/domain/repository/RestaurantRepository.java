package com.alexcode.eatgo.domain.repository;

import com.alexcode.eatgo.domain.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

  List<Restaurant> findAll();

  List<Restaurant> findAllByRegionIdAndCategoryId(Long regionId, Long categoryId);

  Optional<Restaurant> findById(Long id);

  Restaurant save(Restaurant restaurant);
}
