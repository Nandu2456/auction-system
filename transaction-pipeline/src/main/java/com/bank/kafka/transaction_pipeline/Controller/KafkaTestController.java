package com.bank.kafka.transaction_pipeline.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.kafka.transaction_pipeline.KafkaTransactionProducer;
import com.bank.kafka.transaction_pipeline.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/transactions")
public class KafkaTestController {

    private final KafkaTransactionProducer producer;
   
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaTestController(KafkaTransactionProducer producer) {
        this.producer = producer;
    }

   @PostMapping("/send")
public ResponseEntity<String> send(@RequestBody Transaction transaction) {
    producer.sendTransaction(transaction);
    return ResponseEntity.ok("Sent to Kafka"+" "+transaction.toString());
}


        
    }

