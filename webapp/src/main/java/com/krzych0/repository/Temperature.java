package com.krzych0.repository;

import org.springframework.data.annotation.Id;

/**
 *
 */
public class Temperature {

  @Id
  public String id;

  public double value;

  public Temperature(double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("Temperature[id=%s, value='%f']", id, value);
  }
}
