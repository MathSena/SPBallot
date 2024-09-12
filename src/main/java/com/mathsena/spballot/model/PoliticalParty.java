package com.mathsena.spballot.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "politicalParties")
public class PoliticalParty {
    @Id
    private String id;
    private String name;
    private String abbreviation;
    private String ideology;
    private String foundationDate;
    private String logoUrl;
}

