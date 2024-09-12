package com.mathsena.spballot.controller;

import com.mathsena.spballot.dto.PoliticalPartyDTO;
import com.mathsena.spballot.service.PoliticalPartyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/political-parties")
@AllArgsConstructor
@CrossOrigin
public class PoliticalPartyController {

  private final PoliticalPartyService politicalPartyService;

  @PostMapping
  public ResponseEntity<PoliticalPartyDTO> createPoliticalParty(
      @RequestBody PoliticalPartyDTO politicalPartyDTO) {
    PoliticalPartyDTO savedParty = politicalPartyService.createPoliticalParty(politicalPartyDTO);
    return ResponseEntity.ok(savedParty);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PoliticalPartyDTO> getPoliticalParty(@PathVariable String id) {
    return politicalPartyService
        .getPoliticalPartyById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<PoliticalPartyDTO>> getAllPoliticalParties() {
    List<PoliticalPartyDTO> politicalParties = politicalPartyService.getAllPoliticalParties();
    return ResponseEntity.ok(politicalParties);
  }
}
