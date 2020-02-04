package com.imdbapp.app.batch.ratings;

import com.imdbapp.app.DAO.entities.Principals;
import com.imdbapp.app.DAO.entities.PrincipalsFromFile;
import com.imdbapp.app.DAO.entities.Ratings;
import com.imdbapp.app.DAO.entities.RatingsFromFile;
import com.imdbapp.app.batch.principles.PrincipalsDataValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class RatingsFileReaderBatchJob {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Autowired
    EntityManagerFactory emf;


    @Value("classpath:/input/tt-Ratings.tsv")
    private Resource inputResource;

    @Bean
    public Step readRatingsStep() {
        return stepBuilderFactory
                .get("step")
                .<RatingsFromFile, Ratings>chunk(100)
                .reader(ratingsReader())
                .processor(ratingsProcessor())
                .writer(ratingsWriter())
                .build();
    }

    @Bean
    public ItemProcessor<RatingsFromFile, Ratings> ratingsProcessor() {
        return new RatingsDataValidator();
    }

    @Bean
    public FlatFileItemReader<RatingsFromFile> ratingsReader() {
        FlatFileItemReader<RatingsFromFile> itemReader = new FlatFileItemReader<RatingsFromFile>();
        itemReader.setLineMapper(ratingsLineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }
    @Bean
    public LineMapper<RatingsFromFile> ratingsLineMapper() {
        DefaultLineMapper<RatingsFromFile> lineMapper = new DefaultLineMapper<RatingsFromFile>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB);
        lineTokenizer.setNames("tconst","averageRating", "numOfVotes");
        BeanWrapperFieldSetMapper<RatingsFromFile> fieldSetMapper = new BeanWrapperFieldSetMapper<RatingsFromFile>();
        fieldSetMapper.setTargetType(RatingsFromFile.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
    @Bean
    public JpaItemWriter ratingsWriter() {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
}
