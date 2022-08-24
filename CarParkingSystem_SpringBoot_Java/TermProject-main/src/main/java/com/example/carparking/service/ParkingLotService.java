package com.example.carparking.service;

import com.example.carparking.dao.ParkingLotDAO;
import com.example.carparking.entity.Car;
import com.example.carparking.entity.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ParkingLotService {
    @Autowired
    private ParkingLotDAO parkingLotDAO;

    public Collection<ParkingLot> getParkingLots(){
        return parkingLotDAO.getParkingLots();
    }

    public Collection<ParkingLot> getEmptyLots(String size) {
        return parkingLotDAO.getEmptyLots(size);
    }

    public ParkingLot updateParkingLot(ParkingLot parkingLot){
        return parkingLotDAO.updateParkingLot(parkingLot);
    }

//    public Collection<ParkingLot> getMediumEmptyLots() {
//        return parkingLotDAO.getMediumEmptyLots();
//    }
//
//    public Collection<ParkingLot> getSmallEmptyLots() {
//        return parkingLotDAO.getSmallEmptyLots();
//    }

//    public ParkingLot getEmptyLot() {
//        return parkingLotDAO.getEmptyLot();
//    }

//    public ParkingLot createParkingLot(ParkingLot parkingLot){
//        return parkingLotDAO.createParkingLot(parkingLot);
//    }
}
