package com.imdbapp.app.services;

import com.imdbapp.app.DAO.sprocs.TopGenresStoredProcedure;
import com.imdbapp.app.DTO.RatingsByGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("topTitlesServiceImpl")
public class TopTitlesServiceImpl implements TopTitlesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RatingsByGenre> findTopTitlesByGenre(String genre, int numberOfResults) {

        TopGenresStoredProcedure sproc = new TopGenresStoredProcedure(jdbcTemplate);
        return sproc.topTitlesByGenre(genre,numberOfResults);
    }
}
