package com.imdbapp.app.DAO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "tconst", unique = true)
    private String tconst;
    @Column(name = "averageRating")
    private Double averageRating;
    @Column(name = "numberOfVotes")
    private Long numberOfVotes;
}
