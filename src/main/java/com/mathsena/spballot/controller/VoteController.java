package com.mathsena.spballot.controller;

import com.mathsena.spballot.dto.VoteDTO;
import com.mathsena.spballot.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votes")
@AllArgsConstructor
@CrossOrigin
public class VoteController {

  private final VoteService votoService;

  @PostMapping("/election/{electionId}/vote")
  public ResponseEntity<VoteDTO> registerVote(
      @PathVariable String electionId, @RequestBody VoteDTO votoDTO) {
    votoDTO.setElectionId(electionId);
    VoteDTO registeredVote = votoService.registerVote(votoDTO);
    return ResponseEntity.ok(registeredVote);
  }
}
