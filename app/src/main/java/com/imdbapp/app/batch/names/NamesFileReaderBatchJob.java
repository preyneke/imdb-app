package com.imdbapp.app.batch.names;

import com.imdbapp.app.DAO.entities.Names;
import com.imdbapp.app.DAO.entities.NamesFromFile;
import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DAO.entities.TitlesFromFile;
import com.imdbapp.app.batch.titles.TitlesDataValidator;
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
public class NamesFileReaderBatchJob {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Autowired
    EntityManagerFactory emf;


    @Value("classpath:/input/nn-Names.tsv")
    private Resource inputResource;


    @Bean
    public Step readNamesStep() {
        return stepBuilderFactory
                .get("step")
                .<NamesFromFile, Names>chunk(100)
                .reader(namereader())
                .processor(nameprocessor())
                .writer(namewriter())
                .build();
    }

    @Bean
    public ItemProcessor<NamesFromFile, Names> nameprocessor() {
        return new NamesDataValidator();
    }

    @Bean
    public FlatFileItemReader<NamesFromFile> namereader() {
        FlatFileItemReader<NamesFromFile> itemReader = new FlatFileItemReader<NamesFromFile>();
        itemReader.setLineMapper(namelineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }
    @Bean
    public LineMapper<NamesFromFile> namelineMapper() {
        DefaultLineMapper<NamesFromFile> lineMapper = new DefaultLineMapper<NamesFromFile>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB);
        lineTokenizer.setNames("nconst", "primaryName", "birthYear", "deathYear", "primaryProfession", "knownForTitles");
        BeanWrapperFieldSetMapper<NamesFromFile> fieldSetMapper = new BeanWrapperFieldSetMapper<NamesFromFile>();
        fieldSetMapper.setTargetType(NamesFromFile.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
    @Bean
    public JpaItemWriter namewriter() {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
}

