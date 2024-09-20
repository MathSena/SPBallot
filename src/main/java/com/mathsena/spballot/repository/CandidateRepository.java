package com.mathsena.spballot.repository;

import com.mathsena.spballot.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {

  Optional<Candidate> findByName(String name);
}
