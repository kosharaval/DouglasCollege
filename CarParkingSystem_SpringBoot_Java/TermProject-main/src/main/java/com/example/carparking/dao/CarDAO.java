package com.example.carparking.dao;

import com.example.carparking.entity.Car;
import com.example.carparking.entity.Owner;
import com.example.carparking.entity.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class CarDAO {
    @Autowired
    private CarRepository carRepository;

    public Collection<Car> getCars(){
        return carRepository.findAll();
    }

    public Car createCar(Car car){
        return carRepository.insert(car);
    }

    public Optional<Car> getCar(Integer carId){
        return carRepository.findById(carId);
    }

    public Car updateCar(Car car){
        return carRepository.save(car);
    }
}
