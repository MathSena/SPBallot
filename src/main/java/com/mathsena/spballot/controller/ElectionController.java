package com.mathsena.spballot.controller;

import com.mathsena.spballot.dto.ElectionDTO;
import com.mathsena.spballot.dto.ElectionResultDTO;
import com.mathsena.spballot.service.ElectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/elections")
@AllArgsConstructor
@CrossOrigin
public class ElectionController {

  private final ElectionService electionService;

  @PostMapping
  public ResponseEntity<ElectionDTO> createElection(@RequestBody ElectionDTO electionDTO) {
    ElectionDTO savedElection = electionService.createElection(electionDTO);
    return ResponseEntity.ok(savedElection);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ElectionDTO> getElection(@PathVariable String id) {
    return electionService
        .getElectionById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<ElectionDTO>> getAllElections() {
    List<ElectionDTO> elections = electionService.getAllElections();
    return ResponseEntity.ok(elections);
  }

  @PostMapping("/{id}/add-candidates")
  public ResponseEntity<ElectionDTO> addCandidatesToElection(
      @PathVariable String id, @RequestBody ElectionDTO electionDTO) {
    List<String> candidateIds = electionDTO.getCandidateIds();
    ElectionDTO updatedElection = electionService.addCandidatesToElection(id, candidateIds);
    return ResponseEntity.ok(updatedElection);
  }

  @GetMapping("/{electionId}/results")
  public ResponseEntity<ElectionResultDTO> getElectionResults(@PathVariable String electionId) {
    ElectionResultDTO results = electionService.getElectionResults(electionId);
    return ResponseEntity.ok(results);
  }
}
