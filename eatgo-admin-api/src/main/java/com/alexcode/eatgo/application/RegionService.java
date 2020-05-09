package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.Region;
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

  public Region addRegion(String name) {
    Region region = Region.builder().name(name).build();
    regionRepository.save(region);
    return region;
  }
}
