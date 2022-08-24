package com.example.carparking.controller;

import com.example.carparking.entity.Car;
import com.example.carparking.entity.Owner;
import com.example.carparking.entity.ParkingLot;
import com.example.carparking.service.CarService;
import com.example.carparking.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping
    public Collection<Car> getCars(){
        return carService.getCars();
    }

    @GetMapping("/{carId}")
    public Optional<Car> getCar(@PathVariable("carId") Integer carId){
        return carService.getCar(carId);
    }

    @PostMapping
    public Car postCar(@RequestBody Car car){
        car.setCarId(sequenceGeneratorService.getSequenceNumber(Car.SEQUENCE_NAME));
        return carService.createCar(car);
    }

    @PutMapping
    public Car updateCar(@RequestBody Car car){
        return carService.updateCar(car);
    }
}
