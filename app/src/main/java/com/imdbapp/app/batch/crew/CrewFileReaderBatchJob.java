package com.imdbapp.app.batch.crew;

import com.imdbapp.app.DAO.entities.Crew;
import com.imdbapp.app.DAO.entities.CrewFromFile;
import com.imdbapp.app.DAO.entities.Names;
import com.imdbapp.app.DAO.entities.NamesFromFile;
import com.imdbapp.app.batch.names.NamesDataValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
public class CrewFileReaderBatchJob {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Autowired
    EntityManagerFactory emf;


    @Value("classpath:/input/nn-Crew.tsv")
    private Resource inputResource;


    @Bean
    public Step readCrewStep() {
        return stepBuilderFactory
                .get("step")
                .<CrewFromFile, Crew>chunk(100)
                .reader(crewreader())
                .processor(crewprocessor())
                .writer(crewwriter())
                .build();
    }

    @Bean
    public ItemProcessor<CrewFromFile, Crew> crewprocessor() {
        return new CrewDataValidator();
    }

    @Bean
    public FlatFileItemReader<CrewFromFile> crewreader() {
        FlatFileItemReader<CrewFromFile> itemReader = new FlatFileItemReader<CrewFromFile>();
        itemReader.setLineMapper(crewlineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }
    @Bean
    public LineMapper<CrewFromFile> crewlineMapper() {
        DefaultLineMapper<CrewFromFile> lineMapper = new DefaultLineMapper<CrewFromFile>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB);
        lineTokenizer.setNames("tconst", "directors", "writers");
        BeanWrapperFieldSetMapper<CrewFromFile> fieldSetMapper = new BeanWrapperFieldSetMapper<CrewFromFile>();
        fieldSetMapper.setTargetType(CrewFromFile.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
    @Bean
    public JpaItemWriter crewwriter() {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
}

