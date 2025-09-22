package com.bank.kafka.transaction_pipeline.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.kafka.transaction_pipeline.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>
{

}