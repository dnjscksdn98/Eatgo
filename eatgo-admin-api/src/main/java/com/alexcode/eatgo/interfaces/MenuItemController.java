package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.MenuItemService;
import com.alexcode.eatgo.domain.network.SuccessResponse;
import com.alexcode.eatgo.interfaces.dto.MenuItemCreateRequestDto;
import com.alexcode.eatgo.interfaces.dto.MenuItemResponseDto;
import com.alexcode.eatgo.interfaces.dto.MenuItemUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "management/api/v1/restaurants")
public class MenuItemController {

  @Autowired
  private MenuItemService menuItemService;

  @GetMapping(path = "/{restaurantId}/menuitems")
  @PreAuthorize("hasAuthority('menuitem:read')")
  public SuccessResponse<List<MenuItemResponseDto>> list(@PathVariable("restaurantId") Long restaurantId) {
    return menuItemService.listById(restaurantId);
  }

  @PostMapping(path = "/{restaurantId}/menuitems")
  @PreAuthorize("hasAuthority('menuitem:write')")
  public SuccessResponse<MenuItemResponseDto> create(
          @PathVariable("restaurantId") Long restaurantId,
          @Valid @RequestBody MenuItemCreateRequestDto request) {

    return menuItemService.create(request, restaurantId);
  }

  @PatchMapping(path = "/{restaurantId}/menuitems")
  @PreAuthorize("hasAuthority('menuitem:write')")
  public String bulkUpdate(
          @PathVariable("restaurantId") Long restaurantId,
          @Valid @RequestBody List<MenuItemUpdateRequestDto> request) {

    menuItemService.bulkUpdate(request, restaurantId);

    return "{}";
  }

}
