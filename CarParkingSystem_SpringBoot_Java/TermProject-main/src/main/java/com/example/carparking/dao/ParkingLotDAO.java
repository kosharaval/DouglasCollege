package com.example.carparking.dao;

import com.example.carparking.entity.Car;
import com.example.carparking.entity.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ParkingLotDAO {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public Collection<ParkingLot> getParkingLots(){
        return parkingLotRepository.findAll();
    }

    public Collection<ParkingLot> getEmptyLots(String size) {
        return parkingLotRepository.findEmptyLots(size);
    }

    public ParkingLot updateParkingLot(ParkingLot parkingLot){
        return parkingLotRepository.save(parkingLot);
    }

//    public Collection<ParkingLot> getMediumEmptyLots() {
//        return parkingLotRepository.findLargeAndEmptyLots("Medium", true);
//    }
//
//    public Collection<ParkingLot> getSmallEmptyLots() {
//        return parkingLotRepository.findLargeAndEmptyLots("Small", true);
//    }

//    public ParkingLot getEmptyLot() {
//        return parkingLotRepository.findFirstBySize("Large", true);
//    }
}
