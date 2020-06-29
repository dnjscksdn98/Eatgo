package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.MenuItemService;
import com.alexcode.eatgo.domain.models.MenuItem;
import com.alexcode.eatgo.interfaces.dto.MenuItemUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class MenuItemController {

  @Autowired
  private MenuItemService menuItemService;

  @GetMapping("/restaurants/{restaurantId}/menuitems")
  public List<MenuItem> list(@PathVariable("restaurantId") Long restaurantId) {
    return menuItemService.getMenuItems(restaurantId);
  }

  @PatchMapping("/restaurants/{restaurantId}/menuitems")
  public String bulkUpdate(
          @PathVariable("restaurantId") Long restaurantId,
          @Valid @RequestBody List<MenuItemUpdateDto> menuItems) {

    menuItemService.bulkUpdate(restaurantId, menuItems);

    return "{}";
  }

}
