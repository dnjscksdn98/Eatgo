package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.RegionDuplicationException;
import com.alexcode.eatgo.domain.RegionRepository;
import com.alexcode.eatgo.domain.models.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class RegionServiceTest {

  private RegionService regionService;

  @Mock
  private RegionRepository regionRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    regionService = new RegionService(regionRepository);
  }

  @Test
  public void getRegions() {
    List<Region> mockRegions = new ArrayList<>();
    mockRegions.add(Region.builder().name("Seoul").build());

    given(regionRepository.findAll()).willReturn(mockRegions);

//    List<Region> regions = regionService.getRegions();
//    Region region = regions.get(0);
//
//    assertEquals(region.getName(), "Seoul");
  }

  @Test
  public void addRegion() {
//    Region region = regionService.addRegion("Seoul");
//
//    given(regionRepository.findByName("Seoul")).willReturn(null);
//
//    verify(regionRepository).save(any());
//
//    assertEquals(region.getName(), "Seoul");
  }

  @Test
  public void addRegionWithExistedData() {
    given(regionRepository.findByName("Seoul"))
            .willThrow(new RegionDuplicationException("Seoul"));

    verify(regionRepository, never()).save(any());
  }
}