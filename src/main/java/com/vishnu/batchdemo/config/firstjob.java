package com.vishnu.batchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class firstjob {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
 
    @Autowired
    public firstjob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }


    @Bean
    public Job jobOne(){
        return new JobBuilder("first job",jobRepository)
                    .start(jobOneStepOne())
                    .build();
    }

    @Bean
    public Step jobOneStepOne(){
        return new StepBuilder("first step",jobRepository)
                .tasklet(firstStepTasklet(),platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet firstStepTasklet(){
        return (contribution, chunkContext) -> {
            System.out.println("First Tasklet executed");
            return RepeatStatus.FINISHED;
        };
    }
}
