package com.example.springbatch.batch.config;

import com.example.springbatch.batch.listener.JobCompletionNotificationListener;
import com.example.springbatch.batch.record.Customer;
import com.example.springbatch.batch.step.CustomerItemProcessor;
import com.example.springbatch.batch.step.CustomerItemReader;
import com.example.springbatch.batch.step.CustomerItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      DataSourceTransactionManager transactionManager,
                      CustomerItemProcessor processor) {
        return new StepBuilder("step1", jobRepository)
                .<Customer, Customer> chunk(3, transactionManager)
                .reader(reader())
                .processor(processor)
                .writer(fileWriter())
                .build();
    }

    @Bean
    public CustomerItemProcessor processor() {
        return new CustomerItemProcessor();
    }

    @Bean
    public FlatFileItemReader<Customer> reader() {
        return new CustomerItemReader().reader();
    }

    @Bean
    public FlatFileItemWriter<Customer> fileWriter() {
        return new CustomerItemWriter().fileWriter();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
