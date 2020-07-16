package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.EatgoCustomerApiApplicationTests;
import com.alexcode.eatgo.domain.models.MenuItem;
import com.alexcode.eatgo.domain.repository.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MenuItemTest extends EatgoCustomerApiApplicationTests {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    public void create() {
        MenuItem menuItem = MenuItem.builder()
                .name("MenuItem01")
                .content("MenuItem01")
                .price(10000L)
                .status("REGISTERED")
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                //.restaurantId(1L)
                .build();

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        assertNotNull(savedMenuItem);
        assertEquals(savedMenuItem.getName(), "MenuItem01");
    }
}
