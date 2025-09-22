package com.bank.kafka.transaction_pipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.kafka.transaction_pipeline.model.Bid;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BidRepository extends JpaRepository<Bid, Long>{
    @Query("SELECT MAX(b.bidAmount) FROM Bid b WHERE b.productName = :productName")
    Double findMaxBidAmountByProductName(@Param("productName") String productName);
   List<Bid> findByProductName(String productName);
}