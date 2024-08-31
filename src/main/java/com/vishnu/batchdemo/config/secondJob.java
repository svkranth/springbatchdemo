package com.vishnu.batchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.vishnu.batchdemo.proessor.itemProcessor;
import com.vishnu.batchdemo.reader.itemReader;
import com.vishnu.batchdemo.writer.itemWriter;

@Configuration
public class secondJob {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private itemReader itemreader;
    private itemProcessor itemprocessor;
    private itemWriter itemwriter;

    @Autowired
    public secondJob(JobRepository jobRepository, PlatformTransactionManager transactionManager, itemReader itemreader,
            itemProcessor itemprocessor, itemWriter itemwriter) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.itemreader = itemreader;
        this.itemprocessor = itemprocessor;
        this.itemwriter = itemwriter;
    }

    @Bean
    public Job jobTwo(){
        return new JobBuilder("second job",jobRepository)
                    .start(jobTwoStepOne())
                    .build();
    }

    @Bean
    public Step jobTwoStepOne(){
        return new StepBuilder("Second Job", jobRepository)
                   .<String,String>chunk(3,transactionManager)
                   .reader(itemreader)
                   .processor(itemprocessor)
                   .writer(itemwriter)
                   .build();
    }


}
