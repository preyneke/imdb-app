package com.imdbapp.app.services;

import com.imdbapp.app.DAO.sprocs.TopGenresStoredProcedure;
import com.imdbapp.app.DTO.TopTitlesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TopTitlesServiceImpl implements TopTitlesService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TopTitlesDto> findTopTitlesByGenre(String genre, int numberOfResults) {

        //Call Stored Procedure to find top Films per Genre
        TopGenresStoredProcedure sproc = new TopGenresStoredProcedure(jdbcTemplate);
        return sproc.topTitlesByGenre(genre,numberOfResults);
    }
}
