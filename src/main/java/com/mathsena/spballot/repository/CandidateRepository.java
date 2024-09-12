package com.mathsena.spballot.repository;

import com.mathsena.spballot.model.Candidate;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
  }