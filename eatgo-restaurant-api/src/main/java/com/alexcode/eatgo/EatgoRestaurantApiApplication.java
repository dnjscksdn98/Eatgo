package com.alexcode.eatgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class EatgoRestaurantApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EatgoRestaurantApiApplication.class, args);
    }

}
