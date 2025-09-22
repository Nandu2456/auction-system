package com.bank.kafka.transaction_pipeline.controller;

import com.bank.kafka.transaction_pipeline.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
@CrossOrigin(origins = "http://localhost:3000")
public class BidResultController {

    @Autowired
    private BidRepository bidRepository;

    @GetMapping("/{productName}")
    public double getMaxBid(@PathVariable String productName) {
        Double max = bidRepository.findMaxBidAmountByProductName(productName);
        return max != null ? max : 0.0;
    }
}
