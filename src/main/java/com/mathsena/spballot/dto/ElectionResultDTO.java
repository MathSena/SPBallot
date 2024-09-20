package com.mathsena.spballot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectionResultDTO {
  private String electionId;
  private String electionName;
  private Map<String, Integer> candidateVotes;
}
