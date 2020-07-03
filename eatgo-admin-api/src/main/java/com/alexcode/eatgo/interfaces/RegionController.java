package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RegionService;
import com.alexcode.eatgo.domain.models.Region;
import com.alexcode.eatgo.interfaces.dto.RegionCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("admin/api/v1/regions")
public class RegionController {

  @Autowired
  private RegionService regionService;

  @GetMapping
  @PreAuthorize("hasAuthority('region:read')")
  public List<Region> list() {
    return regionService.getRegions();
  }

  @PostMapping
  @PreAuthorize("hasAuthority('region:write')")
  public ResponseEntity<?> create(
          @Valid @RequestBody RegionCreateDto resource) throws URISyntaxException {

    Region region = regionService.addRegion(resource.getName());
    URI location = new URI("/regions/" + region.getId());

    return ResponseEntity.created(location).body("{}");
  }

}
