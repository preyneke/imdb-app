package com.imdbapp.app.DAO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "crew")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Crew  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name ="tconst", unique = true)
    private String tconst;
    @Column(name = "directors")
    private String directors;
    @Column(name ="writers")
    private String writers;
}
