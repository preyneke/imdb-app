package com.imdbapp.app.DAO.repositories;

import com.imdbapp.app.DAO.entities.Principals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrincipalsRepository extends JpaRepository<Principals, Long> {

    List<Principals> findPrincipalsByTconst(String tconst);
}
