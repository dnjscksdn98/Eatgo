package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.domain.Restaurant;
import com.alexcode.eatgo.domain.RestaurantRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

  private RestaurantRepository repository = new RestaurantRepository();

  @GetMapping("/restaurants")
  public List<Restaurant> list() {
    return repository.findAll();
  }

  @GetMapping("/restaurants/{id}")
  public Restaurant detail(@PathVariable("id") Long id) {
    return repository.findById(id);
  }
}
