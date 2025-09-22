package com.bank.kafka.transaction_pipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class TransactionPipelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionPipelineApplication.class, args);
	}

}
