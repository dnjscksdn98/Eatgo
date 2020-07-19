package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.exceptions.RestaurantNotFoundException;
import com.alexcode.eatgo.domain.models.MenuItem;
import com.alexcode.eatgo.domain.models.Restaurant;
import com.alexcode.eatgo.domain.repository.MenuItemRepository;
import com.alexcode.eatgo.domain.repository.RestaurantRepository;
import com.alexcode.eatgo.domain.status.MenuItemStatus;
import com.alexcode.eatgo.exceptions.MenuItemNotFoundException;
import com.alexcode.eatgo.interfaces.dto.MenuItemCreateRequestDto;
import com.alexcode.eatgo.interfaces.dto.MenuItemResponseDto;
import com.alexcode.eatgo.interfaces.dto.MenuItemUpdateRequestDto;
import com.alexcode.eatgo.network.SuccessCode;
import com.alexcode.eatgo.network.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.alexcode.eatgo.security.ApplicationUserRole.ADMIN;

@Service
@Transactional
public class MenuItemService {

  private MenuItemRepository menuItemRepository;
  private RestaurantRepository restaurantRepository;

  @Autowired
  public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
    this.menuItemRepository = menuItemRepository;
    this.restaurantRepository = restaurantRepository;
  }

  public SuccessResponse<List<MenuItemResponseDto>> listById(Long restaurantId) {
    restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(restaurantId);

    return response(menuItems, HttpStatus.OK.value(), SuccessCode.OK);
  }

  public SuccessResponse<MenuItemResponseDto> create(MenuItemCreateRequestDto request, Long restaurantId) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    MenuItem menuItem = MenuItem.builder()
            .name(request.getName())
            .content(request.getContent())
            .price(request.getPrice())
            .status(MenuItemStatus.REGISTERED)
            .createdAt(LocalDateTime.now())
            .createdBy(ADMIN.name())
            .restaurant(restaurant)
            .build();

    MenuItem savedMenuItem = menuItemRepository.save(menuItem);

    return response(savedMenuItem, HttpStatus.CREATED.value(), SuccessCode.MENU_CREATION_SUCCESS);
  }

  public SuccessResponse<?> bulkUpdate(List<MenuItemUpdateRequestDto> request, Long restaurantId) {
    restaurantRepository.findById(restaurantId)
            .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

    for(MenuItemUpdateRequestDto menuItem : request) {
      update(menuItem);
    }

    return SuccessResponse.OK(HttpStatus.OK.value(), SuccessCode.MENU_UPDATE_SUCCESS);
  }

  private void update(MenuItemUpdateRequestDto menuItem) {
    Long menuItemId = menuItem.getId();

    MenuItem menuItemFromDB = menuItemRepository.findById(menuItemId)
            .orElseThrow(() -> new MenuItemNotFoundException(menuItemId));

    if(menuItem.isDestroy()) {
      menuItemRepository.deleteById(menuItemId);
      return;
    }

    menuItemFromDB.updateMenuItem(
            menuItem.getName(),
            menuItem.getContent(),
            menuItem.getPrice(),
            menuItem.getStatus()
    );
  }

  private SuccessResponse<List<MenuItemResponseDto>> response(List<MenuItem> menuItems, Integer status, SuccessCode successCode) {
    List<MenuItemResponseDto> data = menuItems.stream()
            .map(menuItem -> MenuItemResponseDto.builder()
                    .id(menuItem.getId())
                    .name(menuItem.getName())
                    .content(menuItem.getContent())
                    .price(menuItem.getPrice())
                    .status(menuItem.getStatus())
                    .createdAt(menuItem.getCreatedAt())
                    .createdBy(menuItem.getCreatedBy())
                    .updatedAt(menuItem.getUpdatedAt())
                    .updatedBy(menuItem.getUpdatedBy())
                    .build())
            .collect(Collectors.toList());

    return SuccessResponse.OK(data, status, successCode);
  }

  private SuccessResponse<MenuItemResponseDto> response(MenuItem menuItem, Integer status, SuccessCode successCode) {
    MenuItemResponseDto data = MenuItemResponseDto.builder()
            .id(menuItem.getId())
            .name(menuItem.getName())
            .content(menuItem.getContent())
            .price(menuItem.getPrice())
            .status(menuItem.getStatus())
            .createdAt(menuItem.getCreatedAt())
            .createdBy(menuItem.getCreatedBy())
            .updatedAt(menuItem.getUpdatedAt())
            .updatedBy(menuItem.getUpdatedBy())
            .build();

    return SuccessResponse.OK(data, status, successCode);
  }
}
