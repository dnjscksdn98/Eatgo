package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.EatgoCustomerApiApplicationTests;
import com.alexcode.eatgo.domain.models.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestaurantTest extends EatgoCustomerApiApplicationTests {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void create() {
        Restaurant restaurant = Restaurant.builder()
                .name("Restaurant01")
                .address("서울시 강남구")
                .status("REGISTERED")
                .content("Restaurant01")
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                //.categoryId(1L)
                //.regionId(1L)
                //.userId(2L)
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        assertNotNull(savedRestaurant);
        assertEquals(savedRestaurant.getName(), "Restaurant01");
    }
}
