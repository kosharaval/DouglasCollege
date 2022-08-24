package com.example.carparking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "car")
public class Car {
    @Transient
    public static final String SEQUENCE_NAME = "car_sequence";

    @Id
    private Integer carId;
    private String carNumber;
    private Integer ownerId;
    private Integer lotNumber;
    private String carSize;
    private String entryDateAndTime;
    private String exitDateAndTime;
    private double amountPaid;
    private int totalTime;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Integer lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getCarSize() {
        return carSize;
    }

    public void setCarSize(String carSize) {
        this.carSize = carSize;
    }

    public String getEntryDateAndTime() {
        return entryDateAndTime;
    }

    public void setEntryDateAndTime(String entryDateAndTime) {
        this.entryDateAndTime = entryDateAndTime;
    }

    public String getExitDateAndTime() {
        return exitDateAndTime;
    }

    public void setExitDateAndTime(String exitDateAndTime) {
        this.exitDateAndTime = exitDateAndTime;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
