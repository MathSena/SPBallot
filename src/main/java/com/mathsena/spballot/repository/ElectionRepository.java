package com.mathsena.spballot.repository;

import com.mathsena.spballot.model.Election;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends MongoRepository<Election, String> {}
