package com.vishnu.batchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.vishnu.batchdemo.model.instructorCSV;

@Configuration
public class fourthJob {
    private JobRepository jobRepository;
    private PlatformTransactionManager transactionManager;

    private JdbcCursorItemReader<instructorCSV> inJdbcCursorItemReader;
    private FlatFileItemWriter<instructorCSV> instructorCsvWriter;

    @Autowired
    public fourthJob(JobRepository jobRepository, PlatformTransactionManager transactionManager,
            JdbcCursorItemReader<instructorCSV> inJdbcCursorItemReader,
            FlatFileItemWriter<instructorCSV> instructorCsvWriter) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.inJdbcCursorItemReader = inJdbcCursorItemReader;
        this.instructorCsvWriter = instructorCsvWriter;
    }

    @Bean
    public Job jobFour(){
        return new JobBuilder("fourth job",jobRepository)
                    .start(jobFourStepOne())
                    .build();
    }

    @Bean
    public Step jobFourStepOne(){
        return new StepBuilder("Fourth Job step 1", jobRepository)
                   .<instructorCSV,instructorCSV>chunk(3,transactionManager)
                   .reader(inJdbcCursorItemReader)
                   .writer(instructorCsvWriter)
                   .build();
    }    
}
