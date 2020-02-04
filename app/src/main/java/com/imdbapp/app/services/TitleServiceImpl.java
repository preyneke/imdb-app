package com.imdbapp.app.services;

import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DAO.repositories.TitlesRepository;
import com.imdbapp.app.DTO.TitlesDto;
import com.imdbapp.app.exceptions.TitleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("titleServiceImpl")
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitlesRepository titlesRepository;

    @Autowired
    CrewServiceImpl crewService;

    @Autowired
    PrincipalsService principalsService;


    @Override
    public Titles getTitleBytconst(String tConst) {


        return titlesRepository.findTitlesByTconst(tConst);
    }

    @Override
    public List<TitlesDto> getTitlesByTitle(String title, int size) throws TitleNotFoundException {

        try { //fetch list of titles
            Pageable firstxElements = PageRequest.of(0,size);
            Pageable secondPageWithFiveElements = PageRequest.of(1, 5);

            List<Titles> titles = titlesRepository.findTitlesByOriginalTitleContaining(title, firstxElements);
            //return DTO
        if(titles.isEmpty()) {
             throw  new Exception();
            }

            return titles.stream()
                    .map(t -> TitlesDto.builder()
                            .tconst(t.getTconst())
                            .originalTitle(t.getOriginalTitle())
                            .releaseYear(t.getStartYear())
                            .genres(Arrays.asList(t.getGenres().split("\\s*,\\s*")))
                            .crew(crewService.crewMembersByTconst(t.getTconst()))
                            .cast(principalsService.principalsByTconst(t.getTconst()))
                            .build()).collect(Collectors.toList());
        } catch (Exception e) {
            throw new TitleNotFoundException("No title with That name found");
        }
    }
}