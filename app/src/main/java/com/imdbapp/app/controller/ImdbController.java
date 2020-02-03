package com.imdbapp.app.controller;

import com.imdbapp.app.DTO.TitlesDto;
import com.imdbapp.app.batch.names.NamesFileReaderBatchJob;
import com.imdbapp.app.batch.titles.TitlesFileReaderBatchJob;
import com.imdbapp.app.services.TitleServiceImpl;
import com.imdbapp.app.DAO.entities.Titles;
import io.swagger.annotations.Api;
import javassist.bytecode.stackmap.BasicBlock;
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
import org.springframework.web.client.RestClientResponseException;

import java.util.List;


@RestController
@Api(value ="imdb API")
public class ImdbController {

    @Autowired
   private TitleServiceImpl titleService;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobBuilderFactory jbf;
    @Autowired
    TitlesFileReaderBatchJob titlesFileReaderBatchJob;
    @Autowired
    NamesFileReaderBatchJob namesFileReaderBatchJob;


    @GetMapping("/test")
    public String test(String word){
        return "yes";
    }

    @GetMapping("/getTitle")
    public Titles getTitleByTconst(String tconst){

        return titleService.getTitleBytconst(tconst);
    }

    @GetMapping("/getTitlesByTitle")
    public List<TitlesDto> getTitlesByTitle(String title){

        return titleService.getTitlesByTitle(title);
    }

    @PostMapping("/LooadTitlesFile")
    public void loadTitleJob() throws Exception
    {

            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(this.readTitlesJob(), params);

    }

    @PostMapping("/LooadNamesFile")
    public void loadNameJob() throws Exception
    {

        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(this.readNamesFileJob(), params);

    }

    @Bean
    public Job readTitlesJob() {
        return jbf
                .get("readTitlesFileJob")
                .incrementer(new RunIdIncrementer())
                .start(titlesFileReaderBatchJob.readTitlesstep())
                .build();
    }
    @Bean
    public Job readNamesFileJob() {
        return jbf
                .get("readNamesFileJob")
                .incrementer(new RunIdIncrementer())
                .start(namesFileReaderBatchJob.readNamesStep())
                .build();
    }

}
