package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.EatgoCustomerApiApplicationTests;
import com.alexcode.eatgo.domain.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryTest extends EatgoCustomerApiApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        Category category = Category.builder()
                .name("Category01")
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                .build();

        Category savedCategory = categoryRepository.save(category);

        assertNotNull(savedCategory);
        assertEquals(savedCategory.getName(), "Category01");
    }
}
