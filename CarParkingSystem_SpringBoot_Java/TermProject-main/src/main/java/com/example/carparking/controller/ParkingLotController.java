package com.example.carparking.controller;

import com.example.carparking.entity.Car;
import com.example.carparking.entity.ParkingLot;
import com.example.carparking.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/lots")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping
    public Collection<ParkingLot> getParkingLots(){
        return parkingLotService.getParkingLots();
    }

//    @PostMapping
//    public ParkingLot postCar(@RequestBody ParkingLot parkingLot){
//        return parkingLotService.createParkingLot(parkingLot);
//    }


    @GetMapping("/{size}")
    public Collection<ParkingLot> getEmptyLots(@PathVariable("size") String size){
        return parkingLotService.getEmptyLots(size);
    }


    @PutMapping
    public ParkingLot updateParkingLot(@RequestBody ParkingLot parkingLot){
        return parkingLotService.updateParkingLot(parkingLot);
    }

//    @GetMapping("/mediumAndEmpty")
//    public Collection<ParkingLot> getMediumEmptyLots(){
//        return parkingLotService.getLargeEmptyLots();
//    }
//
//    @GetMapping("/smallAndEmpty")
//    public Collection<ParkingLot> getSmallEmptyLots(){
//        return parkingLotService.getSmallEmptyLots();
//    }

//    @GetMapping("/empty")
//    public ParkingLot getEmptyLot(){
//        //System.out.println(parkingLot.getSize());
//        return parkingLotService.getEmptyLot();
//    }
}
