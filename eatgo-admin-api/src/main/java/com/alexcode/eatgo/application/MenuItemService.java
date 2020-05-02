package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.MenuItem;
import com.alexcode.eatgo.domain.MenuItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService {

  @Autowired
  MenuItemRepository menuItemRepository;

  public MenuItemService(MenuItemRepository menuItemRepository) {
    this.menuItemRepository = menuItemRepository;
  }

  public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
    for(MenuItem menuItem : menuItems) {
      update(restaurantId, menuItem);
    }
  }

  private void update(Long restaurantId, MenuItem menuItem) {
    if(menuItem.isDestroy()) {
      menuItemRepository.deleteById(menuItem.getId());
      return;
    }
    menuItem.setRestaurantId(restaurantId);
    menuItemRepository.save(menuItem);
  }
}
