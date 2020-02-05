package com.imdbapp.app.DAO.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "principals")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Principals implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "tconst")
    private String tconst;
    @Column(name = "ordering")
    private String ordering;
    @Column(name = "nconst")
    private String nconst;
    @Column(name = "catagory")
    private String category;
    @Column(name = "job")
    private String job;
    @Column(name = "characters")
    private String characters;
}
