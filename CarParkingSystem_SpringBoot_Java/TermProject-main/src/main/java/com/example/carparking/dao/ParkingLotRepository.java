package com.example.carparking.dao;

import com.example.carparking.entity.ParkingLot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;

public interface ParkingLotRepository extends MongoRepository<ParkingLot, Integer> {
    @Query("{ 'size' : ?0, 'empty' : true }")
    Collection<ParkingLot> findEmptyLots(String size);

//    @Query("{ 'size' : ?0, 'empty' : ?1 }")
//    ParkingLot findFirstBySize(String size, boolean empty);

}
