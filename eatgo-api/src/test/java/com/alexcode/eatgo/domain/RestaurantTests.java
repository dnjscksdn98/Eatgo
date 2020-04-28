package com.alexcode.eatgo.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestaurantTests {

  @Test
  public void creation() {
    Restaurant restaurant = new Restaurant("Bob zip", "Seoul");
    assertThat(restaurant.getName(), is("Bob zip"));
    assertThat(restaurant.getAddress(), is("Seoul"));
  }

  @Test
  public void information() {
    Restaurant restaurant = new Restaurant("Bob zip", "Seoul");
    assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
  }
}