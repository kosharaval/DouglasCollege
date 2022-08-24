package com.example.carparking.service;

import com.example.carparking.dao.CarDAO;
import com.example.carparking.entity.Car;
import com.example.carparking.entity.Owner;
import com.example.carparking.entity.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarDAO carDAO;

    public Collection<Car> getCars(){
        return carDAO.getCars();
    }

    public Car createCar(Car car){
        return carDAO.createCar(car);
    }

    public Optional<Car> getCar(Integer carId){
        return carDAO.getCar(carId);
    }

    public Car updateCar(Car car){
        return carDAO.updateCar(car);
    }
}
