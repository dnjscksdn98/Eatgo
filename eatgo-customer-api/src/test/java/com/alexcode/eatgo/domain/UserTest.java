package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.EatgoCustomerApiApplicationTests;
import com.alexcode.eatgo.domain.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.alexcode.eatgo.security.ApplicationUserRole.CUSTOMER;
import static com.alexcode.eatgo.security.ApplicationUserRole.OWNER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest extends EatgoCustomerApiApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = User.builder()
                .email("tester01@test.com")
                .name("tester01")
                .password("tester01")
                .level(1L)
                .role(CUSTOMER)
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                .build();

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals(savedUser.getEmail(), "tester01@test.com");
    }

    @Test
    public void createOwner() {
        User user = User.builder()
                .email("owner01@test.com")
                .name("owner01")
                .password("owner01")
                .level(50L)
                .role(OWNER)
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                .build();

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertEquals(savedUser.getEmail(), "owner01@test.com");
    }

    @Test
    @Transactional
    public void read() {
        Optional<User> optional = userRepository.findById(2L);

        if(optional.isPresent()) {
            optional.stream()
                    .forEach(user -> {
                        System.out.println("-------------- 사용자 정보 ---------------");
                        System.out.println("이메일 : " + user.getEmail());
                        System.out.println("이름 : " + user.getName());

                        System.out.println("-------------- 가게 정보 -----------------");
                        System.out.println("가게 이름 : " + user.getRestaurant().getName());
                        System.out.println("가게 주소 : " + user.getRestaurant().getAddress());

                        System.out.println("--------------- 카테고리 정보 ---------------");
                        System.out.println("가게 카테고리 : " + user.getRestaurant().getCategory().getName());

                        System.out.println("--------------- 지역 정보 ------------------");
                        System.out.println("가게 지역 정보 : " + user.getRestaurant().getRegion().getName());

                        System.out.println("---------------- 메뉴 정보 -----------------");
                        user.getRestaurant().getMenuItems().stream()
                                .forEach(menuItem -> {
                                    System.out.println("메뉴 이름 : " + menuItem.getName());
                                    System.out.println("메뉴 가격 : " + menuItem.getPrice());
                                });

                        System.out.println("------------------ 리뷰 정보 --------------------");
                        user.getRestaurant().getReviews().stream()
                                .forEach(review -> {
                                    System.out.println("리뷰 내용 : " + review.getContent());
                                    System.out.println("가게 점수 : " + review.getScore());
                                });

                        System.out.println("----------------- 예약 정보 -----------------");
                        user.getRestaurant().getReservations().stream()
                                .forEach(rev -> {
                                    System.out.println("예약 날짜 및 시간 : " + rev.getBookedAt());
                                    System.out.println("예약 인원수 : " + rev.getPartySize());
                                });
                    });
        }
    }
}
