package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DAO.repositories.TitlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("titleServiceImpl")
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitlesRepository titlesRepository;
    @Override
    public Titles getTitleBytconst(String tConst) {



        return titlesRepository.findTitlesByTconst(tConst);
    }
}
