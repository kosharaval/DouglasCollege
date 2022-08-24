package com.example.carparking.dao;

import com.example.carparking.entity.Admin;
import com.example.carparking.entity.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class AdminDAO {
    @Autowired
    private AdminRepository adminRepository;

    public Collection<Admin> getAdmins(String username, String password) {
        return adminRepository.getAdmins(username, password);
    }
}
