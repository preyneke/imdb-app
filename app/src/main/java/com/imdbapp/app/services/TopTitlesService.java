package com.imdbapp.app.services;

import com.imdbapp.app.DTO.TopTitlesDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TopTitlesService {
    List<TopTitlesDto> findTopTitlesByGenre(String genre, int numberOfResults);
}
