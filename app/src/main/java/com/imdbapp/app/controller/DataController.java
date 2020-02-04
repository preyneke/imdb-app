package com.imdbapp.app.controller;

import com.imdbapp.app.batch.crew.CrewFileReaderBatchJob;
import com.imdbapp.app.batch.names.NamesFileReaderBatchJob;
import com.imdbapp.app.batch.principles.PrincipalsFileReaderBatchJob;
import com.imdbapp.app.batch.titles.TitlesFileReaderBatchJob;
import io.swagger.annotations.Api;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value ="imdb API")
public class DataController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobBuilderFactory jbf;

    @Autowired
    TitlesFileReaderBatchJob titlesFileReaderBatchJob;

    @Autowired
    NamesFileReaderBatchJob namesFileReaderBatchJob;

    @Autowired
    CrewFileReaderBatchJob crewFileReaderBatchJob;

    @Autowired
    PrincipalsFileReaderBatchJob principalsFileReaderBatchJob;

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

    @PostMapping("/LooadCrewFile")
    public void loadCrewJob() throws Exception
    {

        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(this.readCrewFileJob(), params);

    }

    @PostMapping("/LooadPrincipalsFile")
    public void loadPrincipalsJob() throws Exception
    {

        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(this.readPrincipalsFileJob(), params);

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

    @Bean
    public Job readCrewFileJob(){
        return jbf
                .get("readCrewFileJob")
                .incrementer(new RunIdIncrementer())
                .start(crewFileReaderBatchJob.readCrewStep())
                .build();
    }

    @Bean
    public Job readPrincipalsFileJob(){
        return jbf
                .get("readPrincipalsFileJob")
                .incrementer(new RunIdIncrementer())
                .start(principalsFileReaderBatchJob.readPrincipalsStep())
                .build();
    }

}
