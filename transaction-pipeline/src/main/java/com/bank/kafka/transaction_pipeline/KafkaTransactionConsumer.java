package com.bank.kafka.transaction_pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bank.kafka.transaction_pipeline.model.Transaction;
import com.bank.kafka.transaction_pipeline.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaTransactionConsumer {

    @Autowired
    private TransactionRepository transactionRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private double currentmaxbid = 0.0; // Initialize max bid

    @KafkaListener(topics = "transaction-topic", groupId = "transaction-validator-group")
    public void consumeTransaction(String message) {
        
        System.out.println("Received transaction: " + message);
        try {
            Transaction transaction = objectMapper.readValue(message, Transaction.class);
            currentmaxbid = Math.max(currentmaxbid, transaction.getBidAmount());
            System.out.println("Current max bid: " + currentmaxbid);
            transactionRepository.save(transaction);
            System.out.println("Saved to DB: " + transaction);
        } catch (Exception e) {
            System.err.println("Failed to parse/save transaction: " + e.getMessage());
        }
    }
}
