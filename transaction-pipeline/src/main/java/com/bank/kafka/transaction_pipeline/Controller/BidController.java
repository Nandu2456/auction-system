package com.bank.kafka.transaction_pipeline.Controller;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;
import com.bank.kafka.transaction_pipeline.BidProducer;
import com.bank.kafka.transaction_pipeline.model.Bid;
import com.bank.kafka.transaction_pipeline.repository.BidRepository;

@RestController
@RequestMapping("/bids")
@CrossOrigin(origins = "http://localhost:3000")
public class BidController {

    @Autowired
    private BidProducer bidProducer;

    @Autowired
    private BidRepository bidRepository;

    // Removed unused OffsetDateTime field

    @PostMapping
    public ResponseEntity<String> placeBid(@RequestBody Bid bid) {
        bid.setTimestamp(OffsetDateTime.now());

        bidProducer.sendBid(bid);
        return ResponseEntity.ok("Bid placed successfully");
    }
    @GetMapping("/results/{productName}")
    public ResponseEntity<Map<String,Integer>> getAuctionResults(@PathVariable String productName) {
        
       List<Bid> bids = bidRepository.findByProductName(productName);
        Map<String, Integer> highestBids = new HashMap<>();
        for (Bid bid : bids) {
            String user = bid.getBidderName();
            int amount = bid.getBidAmount();

            highestBids.put(user, Math.max(highestBids.getOrDefault(user, 0), amount));
        }

        return ResponseEntity.ok(highestBids);
    }
    
}

