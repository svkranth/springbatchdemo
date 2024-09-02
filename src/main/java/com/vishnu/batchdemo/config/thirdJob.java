package com.vishnu.batchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.vishnu.batchdemo.model.instructorCSV;
import com.vishnu.batchdemo.writer.instructorItemWriter;

@Configuration
public class thirdJob {

    private JobRepository jobRepository;
    private PlatformTransactionManager platformTransactionManager;

    private FlatFileItemReader<instructorCSV> instructordetailitemreader;
    private instructorItemWriter instructoritemwriter;

    @Autowired
    public thirdJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager,
            FlatFileItemReader<instructorCSV> instructordetailitemreader, instructorItemWriter instructoritemwriter) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
        this.instructordetailitemreader = instructordetailitemreader;
        this.instructoritemwriter = instructoritemwriter;
    }

    @Bean
    public Job jobThree(){
        return new JobBuilder("third job",jobRepository)
                    .start(jobThreeStepOne())
                    .build();
    }

    @Bean
    public Step jobThreeStepOne(){
        return new StepBuilder("Third Job step 1", jobRepository)
                   .<instructorCSV,instructorCSV>chunk(3,platformTransactionManager)
                   .reader(instructordetailitemreader)
                   .writer(instructoritemwriter)
                   .build();
    }

}
