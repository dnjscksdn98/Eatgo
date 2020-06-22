package com.alexcode.eatgo.application;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

import com.alexcode.eatgo.domain.models.Region;
import com.alexcode.eatgo.domain.RegionRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    List<Region> regions = regionService.getRegions();

    Region region = regions.get(0);
    assertThat(region.getName(), is("Seoul"));
  }

}