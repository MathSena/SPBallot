package com.mathsena.spballot.service;

import com.mathsena.spballot.dto.PoliticalPartyDTO;
import com.mathsena.spballot.model.PoliticalParty;
import com.mathsena.spballot.repository.PoliticalPartyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PoliticalPartyService {

  private final PoliticalPartyRepository politicalPartyRepository;
  private final ModelMapper modelMapper;

  public PoliticalPartyDTO createPoliticalParty(PoliticalPartyDTO politicalPartyDTO) {

    PoliticalParty politicalParty = modelMapper.map(politicalPartyDTO, PoliticalParty.class);
    PoliticalParty savedPoliticalParty = politicalPartyRepository.save(politicalParty);
    log.info("Political party created: {}", savedPoliticalParty);
    return modelMapper.map(savedPoliticalParty, PoliticalPartyDTO.class);
  }

  public Optional<PoliticalPartyDTO> getPoliticalPartyById(String id) {
    log.info("Political party id: {}", id);
    return politicalPartyRepository
        .findById(id)
        .map(politicalParty -> modelMapper.map(politicalParty, PoliticalPartyDTO.class));
  }

  public List<PoliticalPartyDTO> getAllPoliticalParties() {
    log.info("Getting all political parties");
    return politicalPartyRepository.findAll().stream()
        .map(politicalParty -> modelMapper.map(politicalParty, PoliticalPartyDTO.class))
        .collect(Collectors.toList());
  }
}
