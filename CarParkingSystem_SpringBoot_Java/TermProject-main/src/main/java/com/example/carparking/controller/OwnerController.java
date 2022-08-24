package com.example.carparking.controller;

import com.example.carparking.entity.Car;
import com.example.carparking.entity.Owner;
import com.example.carparking.service.OwnerService;
import com.example.carparking.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping
    public Collection<Owner> getOwners(){
        return ownerService.getOwners();
    }

    @GetMapping("/{ownerId}")
    public Optional<Owner> getOwner(@PathVariable("ownerId") Integer ownerId){
        return ownerService.getOwner(ownerId);
    }

    @PostMapping
    public Owner postOwner(@RequestBody Owner owner){
        owner.setOwnerId(sequenceGeneratorService.getSequenceNumber(Owner.SEQUENCE_NAME));
        return ownerService.createOwner(owner);
    }
}
