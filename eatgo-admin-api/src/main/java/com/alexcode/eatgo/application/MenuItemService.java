package com.alexcode.eatgo.application;

import com.alexcode.eatgo.application.exceptions.MenuItemNotFoundException;
import com.alexcode.eatgo.domain.MenuItemRepository;
import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.models.MenuItem;
import com.alexcode.eatgo.interfaces.dto.MenuItemUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

  private MenuItemRepository menuItemRepository;
  private RestaurantRepository restaurantRepository;

  @Autowired
  public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
    this.menuItemRepository = menuItemRepository;
    this.restaurantRepository = restaurantRepository;
  }

  public List<MenuItem> getMenuItems(Long restaurantId) {
    if(restaurantRepository.findById(restaurantId).isEmpty()) {
      throw new RestaurantNotFoundException(restaurantId);
    }
    return menuItemRepository.findAllByRestaurantId(restaurantId);
  }

  public void bulkUpdate(Long restaurantId, List<MenuItemUpdateDto> menuItems) {
    if(restaurantRepository.findById(restaurantId).isEmpty()) {
      throw new RestaurantNotFoundException(restaurantId);
    }
    for(MenuItemUpdateDto menuItem : menuItems) {
      update(menuItem);
    }
  }

  private void update(MenuItemUpdateDto menuItem) {
    Long id = menuItem.getId();
    String name = menuItem.getName();

    MenuItem menuItemFromDB = menuItemRepository.findById(id)
            .orElseThrow(() -> new MenuItemNotFoundException(id));

    if(menuItem.isDestroy()) {
      menuItemRepository.deleteById(id);
      return;
    }

    menuItemFromDB.setName(name);
  }
}
