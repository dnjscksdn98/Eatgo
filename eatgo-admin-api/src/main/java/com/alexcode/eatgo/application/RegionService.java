package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.RegionDuplicationException;
import com.alexcode.eatgo.domain.RegionRepository;
import com.alexcode.eatgo.domain.models.Region;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.RegionRequestDto;
import com.alexcode.eatgo.interfaces.dto.RegionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.alexcode.eatgo.security.ApplicationUserRole.ADMIN;

@Service
@Transactional
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

  public SuccessResponse<RegionResponseDto> create(RegionRequestDto request) {
    String name = request.getName();

    if(regionRepository.findByName(name).isPresent()) {
      throw new RegionDuplicationException(name);
    }

    Region region = Region.builder()
            .name(name)
            .createdAt(LocalDateTime.now())
            .createdBy(ADMIN.name())
            .build();

    Region savedRegion = regionRepository.save(region);

    return response(savedRegion, 201);
  }

  private SuccessResponse<List<RegionResponseDto>> response(List<Region> regions, Integer status) {
    List<RegionResponseDto> data = regions.stream()
            .map(region -> RegionResponseDto.builder()
                    .id(region.getId())
                    .name(region.getName())
                    .createdAt(region.getCreatedAt())
                    .createdBy(region.getCreatedBy())
                    .updatedAt(region.getUpdatedAt())
                    .updatedBy(region.getUpdatedBy())
                    .build())
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status);
  }

  private SuccessResponse<RegionResponseDto> response(Region region, Integer status) {
    RegionResponseDto data = RegionResponseDto.builder()
            .id(region.getId())
            .name(region.getName())
            .createdAt(region.getCreatedAt())
            .createdBy(region.getCreatedBy())
            .updatedAt(region.getUpdatedAt())
            .updatedBy(region.getUpdatedBy())
            .build();

    return SuccessResponse.OK(data, status);
  }
}
