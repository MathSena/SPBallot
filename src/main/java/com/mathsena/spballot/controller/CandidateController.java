package com.mathsena.spballot.controller;

import com.mathsena.spballot.dto.CandidateDTO;
import com.mathsena.spballot.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidates")
@AllArgsConstructor
@CrossOrigin
public class CandidateController {

  private final CandidateService candidateService;

  @PostMapping
  public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CandidateDTO candidateDTO) {
    CandidateDTO savedCandidate = candidateService.createCandidate(candidateDTO);
    return ResponseEntity.ok(savedCandidate);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CandidateDTO> getCandidate(@PathVariable String id) {
    return candidateService
        .getCandidateById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
    List<CandidateDTO> candidates = candidateService.getAllCandidates();
    return ResponseEntity.ok(candidates);
  }
}
