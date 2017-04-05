package com.krzych0.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface TemperatureRepository extends MongoRepository<Temperature, String> {

  List<Temperature> getAllById();
}

