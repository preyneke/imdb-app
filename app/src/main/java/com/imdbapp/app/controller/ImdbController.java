package com.imdbapp.app.controller;

import com.imdbapp.app.DTO.TitlesDto;
import com.imdbapp.app.batch.crew.CrewFileReaderBatchJob;
import com.imdbapp.app.batch.names.NamesFileReaderBatchJob;
import com.imdbapp.app.batch.principles.PrincipalsFileReaderBatchJob;
import com.imdbapp.app.batch.titles.TitlesFileReaderBatchJob;
import com.imdbapp.app.exceptions.TitleNotFoundException;
import com.imdbapp.app.services.TitleServiceImpl;
import com.imdbapp.app.DAO.entities.Titles;
import io.swagger.annotations.Api;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


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

    @GetMapping("/getTitlesByTitle")
    public List<TitlesDto> getTitlesByTitle(String title) throws TitleNotFoundException {

        return titleService.getTitlesByTitle(title);
    }


}
