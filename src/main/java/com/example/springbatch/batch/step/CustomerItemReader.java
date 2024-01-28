package com.example.springbatch.batch.step;

import com.example.springbatch.batch.record.Customer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;

public class CustomerItemReader {

    public FlatFileItemReader<Customer> reader() {

        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerItemReader")
                .resource(new FileSystemResource("/home/oc/lab/custom_2017_2020.csv"))
                .delimited()
                .names("exp_imp", "year", "month", "ym", "country", "custom", "hs2", "hs4", "hs6", "hs9", "q1", "q2", "value")
                .targetType(Customer.class)
                .build();
    }

}
