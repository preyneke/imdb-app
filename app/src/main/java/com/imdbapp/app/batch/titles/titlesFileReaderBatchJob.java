package com.imdbapp.app.batch.titles;

import com.imdbapp.app.DAO.entities.Titles;
import com.imdbapp.app.DAO.entities.TitlesFromFile;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
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

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class titlesFileReaderBatchJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;


    @Value("classpath:/input/tt-Titles.tsv")
    private Resource inputResource;

    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<TitlesFromFile, Titles>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemProcessor<TitlesFromFile, Titles> processor() {
        return new titlesDataValidator();
    }

    @Bean
    public FlatFileItemReader<TitlesFromFile> reader() {
        FlatFileItemReader<TitlesFromFile> itemReader = new FlatFileItemReader<TitlesFromFile>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }
    @Bean
    public LineMapper<TitlesFromFile> lineMapper() {
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
    public JdbcBatchItemWriter<Titles> writer() {
        JdbcBatchItemWriter<Titles> itemWriter = new JdbcBatchItemWriter<Titles>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("INSERT INTO TITLES ( TCONST, TITLETYPE, PRIMARYTITLE, ORIGINALTITLE, ISADULT, STARTYEAR, ENDYEAR, RUNTIME, GENRES) VALUES (:tconst,  :titleType, :primaryTitle, :originalTitle, :isAdult, :startYear, :endYear, :runtime, :genres);");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Titles>());
        return itemWriter;
    }
}
