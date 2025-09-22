package com.bank.kafka.transaction_pipeline;
import com.bank.kafka.transaction_pipeline.model.Bid;
import com.bank.kafka.transaction_pipeline.repository.BidRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
@Service
public class BidFileConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "bids_backup.txt";

    @KafkaListener(topics = "bid-topic", groupId = "file-group")
    public void consume(String message) {
        try {
            System.out.println("Received message: " + message);
            Files.write(Paths.get(path), (message + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
