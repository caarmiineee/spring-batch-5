package com.example.springbatch.batch.step;

import com.example.springbatch.batch.record.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

	private static final Logger log = LoggerFactory.getLogger(CustomerItemProcessor.class);

	@Override
	public Customer process(final Customer customer) {
		final String expImp = "EXPORT"; // Cambiato il valore di expImp
		final String year = "2023"; // Cambiato il valore di year
		final String month = "December"; // Cambiato il valore di month
		final String ym = "202312"; // Cambiato il valore di ym
		final String country = "United States"; // Cambiato il valore di country
		final String custom = "Customs Department"; // Cambiato il valore di custom
		final String hs2 = "1234"; // Cambiato il valore di hs2
		final String hs4 = "5678"; // Cambiato il valore di hs4
		final String hs6 = "91011"; // Cambiato il valore di hs6
		final String hs9 = "12131415"; // Cambiato il valore di hs9
		final String q1 = "1000"; // Cambiato il valore di q1
		final String q2 = "2000"; // Cambiato il valore di q2
		final String value = "500000"; // Cambiato il valore di value

		final Customer transformedCustomer = new Customer(
				expImp, year, month, ym, country, custom, hs2, hs4, hs6, hs9, q1, q2, value
		);

		log.info("Converting (" + customer + ") into (" + transformedCustomer + ")");

		return transformedCustomer;
	}

}
