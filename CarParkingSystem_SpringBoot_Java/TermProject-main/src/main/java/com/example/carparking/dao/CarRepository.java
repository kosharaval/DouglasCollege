package com.example.carparking.dao;

import com.example.carparking.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, Integer> {
}
