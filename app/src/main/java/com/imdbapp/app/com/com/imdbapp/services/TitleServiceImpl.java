package com.imdbapp.app.com.com.imdbapp.services;

import com.imdbapp.app.com.imdbapp.DAO.entities.Titles;
import com.imdbapp.app.com.imdbapp.DAO.repositories.TitlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
