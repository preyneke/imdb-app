package com.imdbapp.app.DAO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name="titles")
@NoArgsConstructor
@AllArgsConstructor
public class Titles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name ="tConst", unique = true)
     private String tconst;
    @Column(name = "titleType")
     private String titleType ;
    @Column (name ="primaryTitle")
     private String primaryTitle;
    @Column (name= "originalTitle")
    private  String originalTitle;
    @Column (name = "isAdult")
    private Boolean isAdult;
    @Column (name = "startYear")
    private Long startYear ;
    @Column (name = "endYear")
    private Long endYear;
    @Column (name ="runTime")
    private Long runtime;
    @Column (name = "genres")
    private String genres;

}
