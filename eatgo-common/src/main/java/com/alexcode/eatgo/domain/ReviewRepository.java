package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.domain.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findAll();

  List<Review> findAllByRestaurantId(Long restaurantId);

  List<Review> findAllByRestaurantIdAndUserId(Long restaurantId, Long userId);

  Review save(Review review);
}
