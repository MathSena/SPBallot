package com.mathsena.spballot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {
  private String id;
  private String name;
  private Integer age;
  private String gender;
  private String politicalPosition;
  private String biography;
  private String photoUrl;
  private String partyId;
}
