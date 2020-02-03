package com.imdbapp.app.DAO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NamesFromFile {
    private String nconst;

    private String primaryName;

    private String birthYear;

    private String deathYear;

    private String primaryProfession;

    private String knownForTitles;
}
