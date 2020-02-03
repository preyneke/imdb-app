package com.imdbapp.app.DAO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TitlesFromFile {

    private String tconst;

    private String titleType ;

    private String primaryTitle;

    private  String originalTitle;

    private Boolean isAdult;

    private String startYear ;

    private String endYear;

    private String runtime;

    private String genres;
}
