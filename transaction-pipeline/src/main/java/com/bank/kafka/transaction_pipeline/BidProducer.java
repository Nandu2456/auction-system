package  com.bank.kafka.transaction_pipeline;

import com.bank.kafka.transaction_pipeline.repository.BidRepository;
import com.bank.kafka.transaction_pipeline.model.Bid;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BidProducer {
    @Autowired
    private KafkaTemplate <String,Bid> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendBid(Bid bid) {
        try {
            kafkaTemplate.send("bid-topic", bid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}