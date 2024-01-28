package com.example.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
@EnableBatchProcessing
public class SpringbatchApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbatchApplication.class, args);
		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		Job importUserJob = context.getBean("importUserJob", Job.class);

		try {
			jobLauncher.run(importUserJob, new JobParameters());
		} catch (JobExecutionException e) {
			// Gestire l'eccezione in base alle esigenze dell'applicazione
			e.printStackTrace();
		}
	}
}
