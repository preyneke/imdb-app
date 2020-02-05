package com.imdbapp.app.DAO.repositories;

import com.imdbapp.app.DAO.entities.Titles;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface TitlesRepository extends PagingAndSortingRepository<Titles, Integer> {
List<Titles>  findTitlesByTconstIn(List<String> tconst);
List<Titles> findTitlesByOriginalTitleContaining(String title, Pageable pageable);
}
