package com.alexcode.eatgo.application;

import com.alexcode.eatgo.domain.MenuItemRepository;
import com.alexcode.eatgo.domain.RestaurantRepository;
import com.alexcode.eatgo.domain.models.MenuItem;
import com.alexcode.eatgo.domain.models.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class MenuItemServiceTest {

  private MenuItemService menuItemService;

  @Mock
  private MenuItemRepository menuItemRepository;

  @Mock
  private RestaurantRepository restaurantRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    menuItemService = new MenuItemService(menuItemRepository, restaurantRepository);
  }

  @Test
  public void getMenuItems() {
    List<MenuItem> mockMenuItems = new ArrayList<>();
    mockMenuItems.add(MenuItem.builder().name("Kimchi").build());
    Restaurant restaurant = Restaurant.builder().id(1004L).build();

    given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(mockMenuItems);

//    List<MenuItem> menuItems = menuItemService.getMenuItems(1004L);
//    MenuItem menuItem = menuItems.get(0);
//
//    assertEquals(menuItem.getName(), "Kimchi");
  }

}