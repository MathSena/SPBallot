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
    // Buscar o partido pelo partyId
    PoliticalParty party =
        politicalPartyRepository
            .findById(candidateDTO.getPartyId())
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Partido não encontrado para o ID: " + candidateDTO.getPartyId()));

    // Mapear o DTO para entidade Candidate
    Candidate candidate = modelMapper.map(candidateDTO, Candidate.class);

    // Associar o partido ao candidato
    candidate.setParty(party);

    // Salvar o candidato
    Candidate savedCandidate = candidateRepository.save(candidate);

    // Mapear o candidato salvo de volta para DTO
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
