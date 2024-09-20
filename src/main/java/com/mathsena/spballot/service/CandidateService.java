package com.mathsena.spballot.service;

import com.mathsena.spballot.dto.CandidateDTO;
import com.mathsena.spballot.model.Candidate;
import com.mathsena.spballot.model.PoliticalParty;
import com.mathsena.spballot.repository.CandidateRepository;
import com.mathsena.spballot.repository.PoliticalPartyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidateService {

  private final CandidateRepository candidateRepository;
  private final PoliticalPartyRepository politicalPartyRepository;
  private final ModelMapper modelMapper;

  public CandidateDTO createCandidate(CandidateDTO candidateDTO) {

    Optional<Candidate> existingCandidate = candidateRepository.findByName(candidateDTO.getName());
    if (existingCandidate.isPresent()) {
      throw new RuntimeException("Candidato com o nome " + candidateDTO.getName() + " já existe.");
    }

    PoliticalParty party =
        politicalPartyRepository
            .findById(candidateDTO.getPartyId())
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Partido não encontrado para o ID: " + candidateDTO.getPartyId()));

    Candidate candidate = modelMapper.map(candidateDTO, Candidate.class);

    candidate.setParty(party);

    Candidate savedCandidate = candidateRepository.save(candidate);

    return modelMapper.map(savedCandidate, CandidateDTO.class);
  }

  public Optional<CandidateDTO> getCandidateById(String id) {
    return candidateRepository
        .findById(id)
        .map(candidate -> modelMapper.map(candidate, CandidateDTO.class));
  }

  public List<CandidateDTO> getAllCandidates() {
    return candidateRepository.findAll().stream()
        .map(candidate -> modelMapper.map(candidate, CandidateDTO.class))
        .collect(Collectors.toList());
  }
}
