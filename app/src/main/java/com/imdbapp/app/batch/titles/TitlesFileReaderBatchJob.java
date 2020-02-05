package com.imdbapp.app.batch.titles;

import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DAO.entities.TitlesFromFile;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
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
public class TitlesFileReaderBatchJob {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Autowired
    EntityManagerFactory emf;

    @Value("classpath:/input/tt-Titles.tsv")
    private Resource inputResource;


    @Bean
    public Step readTitlesstep() {
        return stepBuilderFactory
                .get("step")
                .<TitlesFromFile, Titles>chunk(100)
                .reader(titlesreader())
                .processor(titlesprocessor())
                .writer(titleswriter())
                .build();
    }

    @Bean
    public ItemProcessor<TitlesFromFile, Titles> titlesprocessor() {
        return new TitlesDataValidator();
    }

    @Bean
    public FlatFileItemReader<TitlesFromFile> titlesreader() {
        FlatFileItemReader<TitlesFromFile> itemReader = new FlatFileItemReader<TitlesFromFile>();
        itemReader.setLineMapper(titleslineMapper());
        itemReader.setLinesToSkip(0);
        itemReader.setResource(inputResource);
        return itemReader;
    }
    @Bean
    public LineMapper<TitlesFromFile> titleslineMapper() {
        DefaultLineMapper<TitlesFromFile> lineMapper = new DefaultLineMapper<TitlesFromFile>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB);
        lineTokenizer.setNames("tconst", "titleType", "primaryTitle", "originalTitle", "isAdult", "startYear", "endYear", "runtime", "genres");
        BeanWrapperFieldSetMapper<TitlesFromFile> fieldSetMapper = new BeanWrapperFieldSetMapper<TitlesFromFile>();
        fieldSetMapper.setTargetType(TitlesFromFile.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
    @Bean
    public JpaItemWriter titleswriter() {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
}
