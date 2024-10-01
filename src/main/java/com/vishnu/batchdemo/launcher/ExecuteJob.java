package com.vishnu.batchdemo.launcher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecuteJob implements CommandLineRunner{

    private JobLauncher jobLauncher;
    /* 
    @Autowired
    private Job jobOne;
    @Autowired
    private Job jobTwo;
    @Autowired
    private Job jobThree;
    @Autowired
    private Job jobFour;
    */

    private ApplicationContext applicationContext;

    @Autowired
    public ExecuteJob(JobLauncher jobLauncher, ApplicationContext applicationContext) {
        this.jobLauncher = jobLauncher;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        if(args.length > 0){
            String jobName = args[0];
            try{
                Job jobname = (Job) applicationContext.getBean(jobName);
                JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startTime", System.currentTimeMillis())
                .toJobParameters();
                JobExecution jobExecution = jobLauncher.run(jobname, jobParameters);
                System.out.println("Job Status: " + jobExecution.getStatus());
            }catch(Exception e){
                System.out.println("Invalid Jobname passed: " + jobName);
                e.printStackTrace();
            }
        }else{
            System.out.println("No Jobname argument passed");
        }
        /*

        JobExecution secondJobExecution = jobLauncher.run(jobTwo, jobParameters);
        System.out.println("Second job Status: " + secondJobExecution.getStatus());

        JobExecution thirdJobExecution = jobLauncher.run(jobThree, jobParameters);
        System.out.println("Third job status: " + thirdJobExecution.getStatus());

        JobExecution fourthJobExecution = jobLauncher.run(jobFour, jobParameters);
        System.out.println("Fourth Job STatus: " + fourthJobExecution.getStatus());
        */
    }

}
