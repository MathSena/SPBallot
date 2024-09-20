package com.mathsena.spballot.repository;

import com.mathsena.spballot.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VoteRepository extends MongoRepository<Vote, String> {
  List<Vote> findByElectionId(String electionId);
}
