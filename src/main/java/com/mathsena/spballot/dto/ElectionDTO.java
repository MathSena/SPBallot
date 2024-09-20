package com.mathsena.spballot.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectionDTO {
  private String id;
  private String name;
  private LocalDate startDate;
  private LocalDate endDate;
  private String description;

  private List<String> candidateIds;
  private List<String> candidateNames;
}
