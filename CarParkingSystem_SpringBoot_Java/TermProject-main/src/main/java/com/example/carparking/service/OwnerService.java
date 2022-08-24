package com.example.carparking.service;

import com.example.carparking.dao.OwnerDAO;
import com.example.carparking.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private OwnerDAO ownerDAO;

    public Collection<Owner> getOwners(){
        return ownerDAO.getOwners();
    }

    public Optional<Owner> getOwner(Integer ownerId){
        return ownerDAO.getOwner(ownerId);
    }

    public Owner createOwner(Owner owner){
        return ownerDAO.createOwner(owner);
    }
}
