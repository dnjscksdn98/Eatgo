package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RegionService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.RegionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customer/api/v1/regions")
public class RegionController {

  @Autowired
  private RegionService regionService;

  @GetMapping
  @PreAuthorize("hasAuthority('region:read')")
  public SuccessResponse<List<RegionResponseDto>> list() {
    return regionService.list();
  }

}
