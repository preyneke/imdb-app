package com.imdbapp.app.DAO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrincipalsFromFile {
    private String tconst;

    private String ordering;

    private String nconst;

    private String category;

    private String job;

    private String characters;
}
