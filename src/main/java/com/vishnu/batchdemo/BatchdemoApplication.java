package com.vishnu.batchdemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//This annotation is not required if spring boot auto configuration is to be used to create metedata tables for batch
@EnableBatchProcessing
@ComponentScan({"com.vishnu.batchdemo","com.vishnu.batchdemo.launcher"})
public class BatchdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchdemoApplication.class, args);
	}

}
