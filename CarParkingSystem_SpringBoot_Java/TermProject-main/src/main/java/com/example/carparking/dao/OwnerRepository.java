package com.example.carparking.dao;

import com.example.carparking.entity.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerRepository extends MongoRepository<Owner, Integer> {
}
