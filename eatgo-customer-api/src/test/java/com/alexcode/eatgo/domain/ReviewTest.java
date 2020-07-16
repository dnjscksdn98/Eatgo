package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.EatgoCustomerApiApplicationTests;
import com.alexcode.eatgo.domain.models.Review;
import com.alexcode.eatgo.domain.repository.ReviewRepository;
import com.alexcode.eatgo.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReviewTest extends EatgoCustomerApiApplicationTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        Review review = Review.builder()
                .score(4.5)
                .content("Nice")
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                //.restaurantId(1L)
                .user(userRepository.getOne(2L))
                .build();

        Review savedReview = reviewRepository.save(review);

        assertNotNull(savedReview);
        assertNotNull(savedReview.getContent(), "Nice");
    }
}
