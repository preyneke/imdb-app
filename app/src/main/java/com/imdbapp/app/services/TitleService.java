package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DTO.TitlesDto;
import com.imdbapp.app.exceptions.TitleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TitleService {
    List<String> getTitlesNameByTconst(List<String> tconst);
    Titles getTitleBytconst(String tConst);
    List<TitlesDto> getTitlesByTitle(String title, int size) throws TitleNotFoundException;
}
