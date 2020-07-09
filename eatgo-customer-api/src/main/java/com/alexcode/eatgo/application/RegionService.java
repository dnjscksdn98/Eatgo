package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.models.Region;
import com.alexcode.eatgo.domain.RegionRepository;
import java.util.List;
import java.util.stream.Collectors;

import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.RegionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

  private RegionRepository regionRepository;

  @Autowired
  public RegionService(RegionRepository regionRepository) {
    this.regionRepository = regionRepository;
  }

  public SuccessResponse<List<RegionResponseDto>> list() {
    List<Region> regions = regionRepository.findAll();

    return response(regions, 200);
  }

  private SuccessResponse<List<RegionResponseDto>> response(List<Region> regions, Integer status) {
    List<RegionResponseDto> data = regions.stream()
            .map(region -> RegionResponseDto.builder()
                    .id(region.getId())
                    .name(region.getName())
                    .build()
            )
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status);
  }

}
