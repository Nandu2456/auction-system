package com.bank.kafka.transaction_pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.bank.kafka.transaction_pipeline.model.Transaction;



@Component
public class KafkaTransactionProducer {

    private final KafkaTemplate<String,Transaction> kafkaTemplate;

    @Autowired
    public KafkaTransactionProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(Transaction transaction) {
        kafkaTemplate.send("transaction-topic",transaction);
    }

}