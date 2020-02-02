package com.imdbapp.app.com.imdbapp.DAO.repositories;

import com.imdbapp.app.com.imdbapp.DAO.entities.Titles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitlesRepository extends JpaRepository<Titles, Long> {
Titles findTitlesByTconst(String tconst);
}
