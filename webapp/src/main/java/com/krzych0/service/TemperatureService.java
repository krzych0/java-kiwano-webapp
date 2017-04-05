package com.krzych0.service;

import com.krzych0.repository.Temperature;
import com.krzych0.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class TemperatureService {

  private final TemperatureRepository repository;

  @Autowired
  public TemperatureService(TemperatureRepository repository) {
    this.repository = repository;
  }

  public void doWork() {
    repository.deleteAll();

    // save a couple of customers
    repository.save(new Temperature(10.f));
    repository.save(new Temperature(20.f));

    // fetch all customers
    System.out.println("Temperature found with findAll():");
    System.out.println("-------------------------------");
    for (Temperature temperature : repository.findAll()) {
      System.out.println(temperature);
    }
    System.out.println();
  }
}
