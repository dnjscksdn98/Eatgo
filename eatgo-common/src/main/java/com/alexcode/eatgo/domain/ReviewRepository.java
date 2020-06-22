package com.alexcode.eatgo.domain;

import java.util.List;

import com.alexcode.eatgo.domain.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

  List<Review> findAll();

  List<Review> findAllByRestaurantId(Long restaurantId);

  Review save(Review review);
}
