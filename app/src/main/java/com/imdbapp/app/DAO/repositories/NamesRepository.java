package com.imdbapp.app.DAO.repositories;

import com.imdbapp.app.DAO.entities.Names;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NamesRepository extends JpaRepository<Names, Long> {
    Names findByNconst(String nconst);
    List<Names> findNamesByKnownForTitlesContains(String tconst);
}
