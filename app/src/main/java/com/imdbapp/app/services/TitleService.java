package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DTO.TitlesDto;
import com.imdbapp.app.exceptions.TitleNotFoundException;

import java.util.List;

public interface TitleService {
    Titles getTitleBytconst(String tConst);
    List<TitlesDto> getTitlesByTitle(String title, int size) throws TitleNotFoundException;
}
