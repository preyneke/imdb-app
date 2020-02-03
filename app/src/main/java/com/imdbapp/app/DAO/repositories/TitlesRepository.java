package com.imdbapp.app.DAO.repositories;

import com.imdbapp.app.DAO.entities.Titles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitlesRepository extends JpaRepository<Titles, Long> {
Titles findTitlesByTconst(String tconst);
}
