package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RegionService;
import com.alexcode.eatgo.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.RegionRequestDto;
import com.alexcode.eatgo.interfaces.dto.RegionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "management/api/v1/regions")
public class RegionController {

  @Autowired
  private RegionService regionService;

  @GetMapping
  @PreAuthorize("hasAuthority('region:read')")
  public SuccessResponse list() {
    return regionService.list();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('region:write')")
  public SuccessResponse<RegionResponseDto> create(@Valid @RequestBody RegionRequestDto request) {
    return regionService.create(request);
  }

}
