package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.MenuItemService;
import com.alexcode.eatgo.interfaces.dto.MenuItemCreateRequestDto;
import com.alexcode.eatgo.interfaces.dto.MenuItemUpdateRequestDto;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "management/api/v1/restaurants")
public class MenuItemController {

  @Autowired
  private MenuItemService menuItemService;

  @GetMapping(path = "/{restaurantId}/menuitems", produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('menuitem:read')")
  public ResponseEntity<SuccessResponse<?>> list(@PathVariable("restaurantId") Long restaurantId) {
    SuccessResponse<?> body = menuItemService.listById(restaurantId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PostMapping(path = "/{restaurantId}/menuitems", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('menuitem:write')")
  public ResponseEntity<SuccessResponse<?>> create(
          @PathVariable("restaurantId") Long restaurantId,
          @Valid @RequestBody MenuItemCreateRequestDto request) {

    SuccessResponse<?> body = menuItemService.create(request, restaurantId);
    return new ResponseEntity<>(body, HttpStatus.CREATED);
  }

  @PatchMapping(path = "/{restaurantId}/menuitems", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize("hasAuthority('menuitem:write')")
  public ResponseEntity<SuccessResponse<?>> bulkUpdate(
          @PathVariable("restaurantId") Long restaurantId,
          @Valid @RequestBody List<MenuItemUpdateRequestDto> request) {

    SuccessResponse<?> body = menuItemService.bulkUpdate(request, restaurantId);
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

}
