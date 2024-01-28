package com.example.springbatch.batch.step;

import com.example.springbatch.batch.record.Customer;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.core.io.FileSystemResource;

public class CustomerItemWriter {

    public FlatFileItemWriter<Customer> fileWriter() {
        return new FlatFileItemWriterBuilder<Customer>()
                .name("customerItemWriter")
                .resource(new FileSystemResource("sample-data-result.csv"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }
}
