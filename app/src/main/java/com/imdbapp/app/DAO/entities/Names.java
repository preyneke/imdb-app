package com.imdbapp.app.DAO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name ="names")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Names {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column( name="nConst",unique = true)
    private String nconst;
    @Column(name = "primaryName")
    private String primaryName;
    @Column(name ="birthYear")
    private Long birthYear;
    @Column(name ="deathYear")
    private Long deathYear;
    @Column(name ="primaryProfession")
    private String primaryProfession;
    @Column(name = "knownForTitles")
    private String knownForTitles;
}
