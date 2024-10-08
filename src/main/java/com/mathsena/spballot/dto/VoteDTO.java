package com.mathsena.spballot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
  private String voterId;
  private String electionId;
  private String candidateId;
}
