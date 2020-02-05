package com.imdbapp.app.services;

import com.imdbapp.app.DTO.RatingsByGenre;

import java.util.List;

public interface TopTitlesService {
    List<RatingsByGenre> findTopTitlesByGenre(String genre, int numberOfResults);
}
