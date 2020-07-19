package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.RegionService;
import com.alexcode.eatgo.interfaces.dto.RegionRequestDto;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "management/api/v1/regions")
public class RegionController {

  @Autowired
  private RegionService regionService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('region:read')")
  public ResponseEntity<SuccessResponse<?>> list() {
    SuccessResponse<?> body = regionService.list();
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('region:write')")
  public ResponseEntity<SuccessResponse<?>> create(@Valid @RequestBody RegionRequestDto request) {
    SuccessResponse<?> body = regionService.create(request);
    return new ResponseEntity<>(body, HttpStatus.CREATED);
  }

}
