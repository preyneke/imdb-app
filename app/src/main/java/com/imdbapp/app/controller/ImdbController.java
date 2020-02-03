package com.imdbapp.app.controller;

import com.imdbapp.app.services.TitleServiceImpl;
import com.imdbapp.app.DAO.entities.Titles;
import io.swagger.annotations.Api;
import javassist.bytecode.stackmap.BasicBlock;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;


@RestController
@Api(value ="imdb API")
public class ImdbController {

    @Autowired
   private TitleServiceImpl titleService;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping("/test")
    public String test(String word){
        return "yes";
    }

    @GetMapping("/getTitle")
    public Titles getTitleByTconst(String tconst){

        return titleService.getTitleBytconst(tconst);
    }

    @PostMapping("/LooadFile")
    public void loadTitleJob() throws Exception
    {
        try{
            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(job, params);
        } catch(Exception e){
            throw new RestClientResponseException(e.getMessage(), 500, "Bad Data", null,null,null);
        }
    }
}
