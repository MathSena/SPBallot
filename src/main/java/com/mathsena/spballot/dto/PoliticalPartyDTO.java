package com.mathsena.spballot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoliticalPartyDTO {
  private String id;
  private String name;
  private String abbreviation;
  private String ideology;
  private String foundationDate;
  private String logoUrl;
}
