package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DTO.TitlesDto;

import java.util.List;

public interface TitleService {
    Titles getTitleBytconst(String tConst);
    List<TitlesDto> getTitlesByTitle(String title);
}
