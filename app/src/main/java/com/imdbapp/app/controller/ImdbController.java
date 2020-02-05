package com.imdbapp.app.controller;

import com.imdbapp.app.DTO.ActorDto;
import com.imdbapp.app.DTO.KnownForActorsByTitleDto;
import com.imdbapp.app.DTO.TopTitlesDto;
import com.imdbapp.app.DTO.TitlesDto;
import com.imdbapp.app.exceptions.TitleNotFoundException;
import com.imdbapp.app.services.*;
import com.imdbapp.app.DAO.entities.Titles;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@Api(value ="imdb API")
public class ImdbController {

    @Autowired
    TitleService titleService;
    @Autowired
    TopTitlesService topTitlesService;
    @Autowired
    PrincipalsService principalsService;




    @GetMapping("/test")
    public String test(String word){
        return "yes";
    }

    @GetMapping("/getTitle")
    public Titles getTitleByTconst(String tconst){

        return titleService.getTitleBytconst(tconst);
    }

    @GetMapping("/getTitlesByTitle")
    public List<TitlesDto> getTitlesByTitle(@RequestParam("search") String title, @RequestParam("resultSize") int size) throws TitleNotFoundException {

        return titleService.getTitlesByTitle(title, size);
    }

    @GetMapping("/topTitles")
    public List<TopTitlesDto> getTopTitlesByGenre(@RequestParam("genre") String genre, @RequestParam("numberOfResults") int size) throws TitleNotFoundException {

        return topTitlesService.findTopTitlesByGenre(genre,size) ;
    }

    @GetMapping("/actors")
    public List<ActorDto> getActorByName(@RequestParam("name") String name) throws TitleNotFoundException {

        return principalsService.listOfActorsByName(name);
    }

    @GetMapping("/actorsByTitle")
    public KnownForActorsByTitleDto getActorByTiles(@RequestParam("title") String title) throws TitleNotFoundException {

        return principalsService.listOfActorsByTitle(title);
    }


}
