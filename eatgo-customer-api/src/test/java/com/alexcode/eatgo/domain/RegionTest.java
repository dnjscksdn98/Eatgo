package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.EatgoCustomerApiApplicationTests;
import com.alexcode.eatgo.domain.models.Region;
import com.alexcode.eatgo.domain.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegionTest extends EatgoCustomerApiApplicationTests {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void create() {
        Region region = Region.builder()
                .name("Region01")
                .createdAt(LocalDateTime.now())
                .createdBy("AdminServer")
                .build();

        Region savedRegion = regionRepository.save(region);

        assertNotNull(savedRegion);
        assertEquals(savedRegion.getName(), "Region01");
    }
}
