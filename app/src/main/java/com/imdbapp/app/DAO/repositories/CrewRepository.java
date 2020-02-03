package com.imdbapp.app.DAO.repositories;

import com.imdbapp.app.DAO.entities.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {
    Crew findByTconst(String tconst);
}
