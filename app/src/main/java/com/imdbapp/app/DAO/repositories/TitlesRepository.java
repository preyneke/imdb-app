package com.imdbapp.app.DAO.repositories;

import com.imdbapp.app.DAO.entities.Titles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TitlesRepository extends JpaRepository<Titles, Long> {
Titles findTitlesByTconst(String tconst);
List<Titles> findTitlesByOriginalTitleContaining(String title);
}
