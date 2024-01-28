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
import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class BatchConfig {

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        writeColNames();
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
                .<Customer, Customer> chunk(1000, transactionManager)
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

    private void writeColNames() {
        String[] columnNames = {"exp_imp", "year", "month", "ym", "country", "custom", "hs2", "hs4", "hs6", "hs9", "q1", "q2", "value"};
        String filePath = "/home/oc/lab/custom_update.csv";
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < columnNames.length; i++) {
                writer.append(columnNames[i]);
                if (i != columnNames.length - 1) {
                    writer.append(",");
                } else {
                    writer.append("\n");
                }
            }
            writer.flush();
            System.out.println("I nomi delle colonne sono stati scritti nel file: " + filePath);
        } catch (IOException e) {
            System.err.println("Si è verificato un errore durante la scrittura dei nomi delle colonne nel file CSV: " + e.getMessage());
        }
    }

}
