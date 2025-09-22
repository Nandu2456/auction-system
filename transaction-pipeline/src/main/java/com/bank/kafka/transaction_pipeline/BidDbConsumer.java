package com.bank.kafka.transaction_pipeline;
import com.bank.kafka.transaction_pipeline.model.Bid;
import com.bank.kafka.transaction_pipeline.repository.BidRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class BidDbConsumer {

    @Autowired
    private BidRepository bidRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public BidDbConsumer(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule()); // Just in case
    }

    @KafkaListener(topics = "bid-topic", groupId = "db-group")
    public void consume(String message) {
        try {
            Bid bid = objectMapper.readValue(message, Bid.class);
            bidRepository.save(bid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
