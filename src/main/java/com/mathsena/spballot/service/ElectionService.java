package com.mathsena.spballot.service;

import com.mathsena.spballot.dto.ElectionDTO;
import com.mathsena.spballot.dto.ElectionResultDTO;
import com.mathsena.spballot.model.Candidate;
import com.mathsena.spballot.model.Election;
import com.mathsena.spballot.repository.CandidateRepository;
import com.mathsena.spballot.repository.ElectionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ElectionService {

  private final ElectionRepository electionRepository;
  private final CandidateRepository candidateRepository;
  private final ModelMapper modelMapper;

  public ElectionDTO createElection(ElectionDTO electionDTO) {

    if (electionDTO.getCandidateIds() == null || electionDTO.getCandidateIds().isEmpty()) {
      throw new IllegalArgumentException("Uma eleição deve ter pelo menos um candidato.");
    }

    Election election = modelMapper.map(electionDTO, Election.class);

    List<Candidate> candidates =
        electionDTO.getCandidateIds().stream()
            .map(candidateRepository::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());

    election.setCandidates(candidates);

    Election savedElection = electionRepository.save(election);

    ElectionDTO result = modelMapper.map(savedElection, ElectionDTO.class);
    result.setCandidateNames(
        candidates.stream().map(Candidate::getName).collect(Collectors.toList()));

    return result;
  }

  public Optional<ElectionDTO> getElectionById(String id) {
    return electionRepository
        .findById(id)
        .map(
            election -> {
              ElectionDTO dto = modelMapper.map(election, ElectionDTO.class);

              dto.setCandidateNames(
                  election.getCandidates().stream()
                      .map(Candidate::getName)
                      .collect(Collectors.toList()));
              return dto;
            });
  }

  public List<ElectionDTO> getAllElections() {
    return electionRepository.findAll().stream()
        .map(
            election -> {
              ElectionDTO dto = modelMapper.map(election, ElectionDTO.class);

              dto.setCandidateNames(
                  election.getCandidates().stream()
                      .map(Candidate::getName)
                      .collect(Collectors.toList()));

              dto.setCandidateIds(
                  election.getCandidates().stream()
                      .map(Candidate::getId)
                      .collect(Collectors.toList()));

              return dto;
            })
        .collect(Collectors.toList());
  }

  public ElectionDTO addCandidatesToElection(String electionId, List<String> candidateIds) {

    Election election =
        electionRepository
            .findById(electionId)
            .orElseThrow(
                () -> new RuntimeException("Eleição não encontrada com ID: " + electionId));

    List<Candidate> candidatesToAdd =
        candidateIds.stream()
            .map(candidateRepository::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();

    election.getCandidates().addAll(candidatesToAdd);

    Election updatedElection = electionRepository.save(election);

    ElectionDTO updatedElectionDTO = modelMapper.map(updatedElection, ElectionDTO.class);
    updatedElectionDTO.setCandidateNames(
        updatedElection.getCandidates().stream()
            .map(Candidate::getName)
            .collect(Collectors.toList()));

    return updatedElectionDTO;
  }

  public ElectionResultDTO getElectionResults(String electionId) {

    Election election =
        electionRepository
            .findById(electionId)
            .orElseThrow(
                () -> new RuntimeException("Eleição não encontrada com ID: " + electionId));

    Map<String, Integer> candidateVotes =
        election.getVoteCount().entrySet().stream()
            .collect(
                Collectors.toMap(
                    entry ->
                        election.getCandidates().stream()
                            .filter(candidate -> candidate.getId().equals(entry.getKey()))
                            .map(Candidate::getName)
                            .findFirst()
                            .orElse("Candidato não encontrado"),
                    Map.Entry::getValue));

    return new ElectionResultDTO(election.getId(), election.getName(), candidateVotes);
  }
}
