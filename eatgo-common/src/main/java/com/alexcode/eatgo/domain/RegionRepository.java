package com.alexcode.eatgo.domain;

import com.alexcode.eatgo.domain.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

  List<Region> findAll();

  Optional<Region> findByName(String name);

  Region save(Region region);
}
