package com.mathsena.spballot.model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "elections")
public class Election {

  @Id private String id;
  private String name;
  private LocalDate startDate;
  private LocalDate endDate;
  private String description;
  @DBRef private List<Candidate> candidates;
}
