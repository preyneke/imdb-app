package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DAO.repositories.TitlesRepository;
import com.imdbapp.app.DTO.TitlesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("titleServiceImpl")
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitlesRepository titlesRepository;
    @Override
    public Titles getTitleBytconst(String tConst) {



        return titlesRepository.findTitlesByTconst(tConst);
    }

    @Override
    public List<TitlesDto> getTitlesByTitle(String title) {
        //fetch list of titles
        List<Titles> titles = titlesRepository.findTitlesByOriginalTitleContaining(title);
        //return DTO
        return titles.stream()
                .map( t -> TitlesDto.builder()
                        .tconst(t.getTconst())
                        .originalTitle(t.getOriginalTitle())
                        .releaseYear(t.getStartYear())
                        .genres(Arrays.asList(t.getGenres().split("\\s*,\\s*")))
                        .build()).collect(Collectors.toList());
    }
}
