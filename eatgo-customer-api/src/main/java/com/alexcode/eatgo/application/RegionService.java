package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.repository.RegionRepository;
import com.alexcode.eatgo.domain.models.Region;
import com.alexcode.eatgo.network.SuccessCode;
import com.alexcode.eatgo.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.RegionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

  private RegionRepository regionRepository;

  @Autowired
  public RegionService(RegionRepository regionRepository) {
    this.regionRepository = regionRepository;
  }

  public SuccessResponse<List<RegionResponseDto>> list() {
    List<Region> regions = regionRepository.findAll();

    return response(regions, HttpStatus.OK.value(), SuccessCode.OK);
  }

  private SuccessResponse<List<RegionResponseDto>> response(List<Region> regions, Integer status, SuccessCode successCode) {
    List<RegionResponseDto> data = regions.stream()
            .map(region -> RegionResponseDto.builder()
                    .id(region.getId())
                    .name(region.getName())
                    .build()
            )
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status, successCode);
  }

}
