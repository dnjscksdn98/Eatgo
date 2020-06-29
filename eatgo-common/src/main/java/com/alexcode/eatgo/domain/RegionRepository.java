package com.alexcode.eatgo.domain;

import java.util.List;
import java.util.Optional;

import com.alexcode.eatgo.domain.models.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {

  List<Region> findAll();

  Optional<Region> findByName(String name);

  Region save(Region region);
}
