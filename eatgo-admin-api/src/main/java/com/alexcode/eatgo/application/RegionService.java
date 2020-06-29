package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.RegionDuplicationException;
import com.alexcode.eatgo.domain.RegionRepository;
import com.alexcode.eatgo.domain.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    if(regionRepository.findByName(name).isPresent()) {
      throw new RegionDuplicationException(name);
    }
    Region region = Region.builder().name(name).build();
    regionRepository.save(region);

    return region;
  }
}
