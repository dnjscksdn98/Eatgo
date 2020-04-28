package com.alexcode.eatgo.domain;

public class Restaurant {

  private final String name;
  private final String address;
  private final Long id;

  public Restaurant(Long id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  public String getInformation() {
    return name + " in " + address;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public Long getId() {
    return id;
  }
}
