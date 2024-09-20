package com.mathsena.spballot.model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "votes")
public class Vote {
  @Id private String id;
  private String voterId;

  @DBRef private Election election;

  @DBRef private Candidate candidate;
}
