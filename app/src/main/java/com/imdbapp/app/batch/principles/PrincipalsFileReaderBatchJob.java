package com.imdbapp.app.batch.principles;

import com.imdbapp.app.DAO.entities.Principals;
import com.imdbapp.app.DAO.entities.PrincipalsFromFile;
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
public class PrincipalsFileReaderBatchJob {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Autowired
    EntityManagerFactory emf;


    @Value("classpath:/input/nn-Principles.tsv")
    private Resource inputResource;


    @Bean
    public Step readPrincipalsStep() {
        return stepBuilderFactory
                .get("step")
                .<PrincipalsFromFile, Principals>chunk(100)
                .reader(principalsreader())
                .processor(principalsprocessor())
                .writer(principalsWriter())
                .build();
    }

    @Bean
    public ItemProcessor<PrincipalsFromFile, Principals> principalsprocessor() {
        return new PrincipalsDataValidator();
    }

    @Bean
    public FlatFileItemReader<PrincipalsFromFile> principalsreader() {
        FlatFileItemReader<PrincipalsFromFile> itemReader = new FlatFileItemReader<PrincipalsFromFile>();
        itemReader.setLineMapper(principalslineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }
    @Bean
    public LineMapper<PrincipalsFromFile> principalslineMapper() {
        DefaultLineMapper<PrincipalsFromFile> lineMapper = new DefaultLineMapper<PrincipalsFromFile>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_TAB);
        lineTokenizer.setNames("tconst","ordering", "nconst", "category", "job", "characters");
        BeanWrapperFieldSetMapper<PrincipalsFromFile> fieldSetMapper = new BeanWrapperFieldSetMapper<PrincipalsFromFile>();
        fieldSetMapper.setTargetType(PrincipalsFromFile.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
    @Bean
    public JpaItemWriter principalsWriter() {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
}



