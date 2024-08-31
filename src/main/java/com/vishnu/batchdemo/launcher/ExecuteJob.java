package com.vishnu.batchdemo.launcher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecuteJob implements CommandLineRunner{

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job jobOne;
    @Autowired
    private Job jobTwo;

    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("startTime", System.currentTimeMillis())
                        .toJobParameters();
        
        JobExecution jobExecution = jobLauncher.run(jobOne, jobParameters);

        System.out.println("Job Status: " + jobExecution.getStatus());

        JobExecution secondJobExecution = jobLauncher.run(jobTwo, jobParameters);
        System.out.println("Second job Status: " + secondJobExecution.getStatus());
    }

}
