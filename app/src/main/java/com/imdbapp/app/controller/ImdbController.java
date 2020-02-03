package com.imdbapp.app.controller;

import com.imdbapp.app.services.TitleServiceImpl;
import com.imdbapp.app.DAO.entities.Titles;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value ="imdb API")
public class ImdbController {

    @Autowired
   private TitleServiceImpl titleService;

    @GetMapping("/test")
    public String test(String word){
        return "yes";
    }

    @GetMapping("/getTitle")
    public Titles getTitleByTconst(String tconst){

        return titleService.getTitleBytconst(tconst);
    }
}
