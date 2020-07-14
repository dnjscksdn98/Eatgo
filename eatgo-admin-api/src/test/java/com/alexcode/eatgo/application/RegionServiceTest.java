package com.alexcode.eatgo.application;

import com.alexcode.eatgo.exceptions.RegionDuplicationException;
import com.alexcode.eatgo.domain.RegionRepository;
import com.alexcode.eatgo.domain.models.Region;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.RegionRequestDto;
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

    SuccessResponse response = regionService.list();
    List<Region> regions = (List<Region>) response.getData();
    Region region = regions.get(0);

    assertEquals(region.getName(), "Seoul");
  }

  @Test
  public void addRegion() {
    SuccessResponse response = regionService.create(RegionRequestDto.builder().name("서울").build());
    Region region = (Region) response.getData();

    given(regionRepository.findByName("Seoul")).willReturn(null);

    verify(regionRepository).save(any());

    assertEquals(region.getName(), "Seoul");
  }

  @Test
  public void addRegionWithExistedData() {
    given(regionRepository.findByName("Seoul"))
            .willThrow(new RegionDuplicationException("Seoul"));

    verify(regionRepository, never()).save(any());
  }
}