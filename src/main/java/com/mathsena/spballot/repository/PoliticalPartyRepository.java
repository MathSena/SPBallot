package com.mathsena.spballot.repository;

import com.mathsena.spballot.model.PoliticalParty;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PoliticalPartyRepository extends MongoRepository<PoliticalParty, String> {}
