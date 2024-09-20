package com.mathsena.spballot.service;

import com.mathsena.spballot.dto.VoteDTO;
import com.mathsena.spballot.model.Candidate;
import com.mathsena.spballot.model.Election;
import com.mathsena.spballot.model.Vote;
import com.mathsena.spballot.repository.CandidateRepository;
import com.mathsena.spballot.repository.ElectionRepository;
import com.mathsena.spballot.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteService {

  private final VoteRepository votoRepository;
  private final ElectionRepository electionRepository;
  private final CandidateRepository candidateRepository;

  public VoteDTO registerVote(VoteDTO votoDTO) {

    Election election =
        electionRepository
            .findById(votoDTO.getElectionId())
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Eleição não encontrada com ID: " + votoDTO.getElectionId()));

    Candidate candidate =
        candidateRepository
            .findById(votoDTO.getCandidateId())
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Candidato não encontrado com ID: " + votoDTO.getCandidateId()));

    if (!election.getCandidates().contains(candidate)) {
      throw new RuntimeException("Candidato não pertence à eleição.");
    }

    Vote voto = new Vote();
    voto.setVoterId(votoDTO.getVoterId());
    voto.setElection(election);
    voto.setCandidate(candidate);

    election
        .getVoteCount()
        .put(
            votoDTO.getCandidateId(),
            election.getVoteCount().getOrDefault(votoDTO.getCandidateId(), 0) + 1);

    votoRepository.save(voto);
    electionRepository.save(election);

    return votoDTO;
  }
}
