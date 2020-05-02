package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.MenuItemService;
import com.alexcode.eatgo.domain.MenuItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MenuItemController {

  @Autowired
  private MenuItemService menuItemService;

  @GetMapping("/restaurants/{restaurantId}/menuitems")
  public List<MenuItem> list(@PathVariable("restaurantId") Long restaurantId) {
    return menuItemService.getMenuItems(restaurantId);
  }

  @PatchMapping("/restaurants/{restaurantId}/menuitems")
  public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId,
                           @RequestBody List<MenuItem> menuItems) {
    menuItemService.bulkUpdate(restaurantId, menuItems);

    return "";
  }

}
