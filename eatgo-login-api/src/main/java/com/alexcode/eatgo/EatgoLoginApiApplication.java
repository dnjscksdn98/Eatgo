package com.alexcode.eatgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class EatgoLoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EatgoLoginApiApplication.class, args);
    }

}
