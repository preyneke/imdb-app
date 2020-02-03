package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Titles;

public interface TitleService {
    Titles getTitleBytconst(String tConst);
}
