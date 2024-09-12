package com.mathsena.spballot.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "candidates")
public class Candidate {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private String politicalPosition;
    private String biography;
    private String photoUrl;
    @DBRef
    private PoliticalParty party;
}
