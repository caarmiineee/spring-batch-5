package com.example.springbatch.batch.step;

import com.example.springbatch.batch.record.Customer;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;

public class CustomerItemWriter {

    public FlatFileItemWriter<Customer> fileWriter() {
        FlatFileItemWriter<Customer> itemWriter = new FlatFileItemWriter<>();
        itemWriter.setName("customerItemWriter");
        itemWriter.setResource(new FileSystemResource("/home/oc/lab/custom_update.csv"));
        itemWriter.setLineAggregator(new CustomerLineAggregator());
        return itemWriter;
    }

    private static class CustomerLineAggregator implements LineAggregator<Customer> {

        @Override
        public String aggregate(Customer item) {
            // Format the CSV line as desired
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(item.month()).append(",");
            stringBuilder.append(item.exp_imp()).append(",");
            stringBuilder.append(item.year()).append(",");
            stringBuilder.append(item.ym()).append(",");
            stringBuilder.append(item.country()).append(",");
            stringBuilder.append(item.custom()).append(",");
            stringBuilder.append(item.hs2()).append(",");
            stringBuilder.append(item.hs4()).append(",");
            stringBuilder.append(item.hs6()).append(",");
            stringBuilder.append(item.hs9()).append(",");
            stringBuilder.append(item.q1()).append(",");
            stringBuilder.append(item.q2()).append(",");
            stringBuilder.append(item.value());
            return stringBuilder.toString();
        }
    }

}
