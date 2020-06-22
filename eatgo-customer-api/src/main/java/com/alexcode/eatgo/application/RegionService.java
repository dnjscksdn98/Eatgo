package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.models.Region;
import com.alexcode.eatgo.domain.RegionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

  private RegionRepository regionRepository;

  @Autowired
  public RegionService(RegionRepository regionRepository) {
    this.regionRepository = regionRepository;
  }

  public List<Region> getRegions() {
    return regionRepository.findAll();
  }

}
