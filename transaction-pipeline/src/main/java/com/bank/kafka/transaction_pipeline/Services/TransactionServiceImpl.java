package com.bank.kafka.transaction_pipeline.Services;

import org.springframework.beans.factory.annotation.*;
import java.util.*;
import com.bank.kafka.transaction_pipeline.model.Transaction;
import com.bank.kafka.transaction_pipeline.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService
{
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction)
    {
        return transactionRepository.save(transaction);
    }
    
    @Override
    public List<Transaction> getAllTransactions()
    {
        return transactionRepository.findAll();
    }

     @Override
       public Transaction getTransactionById(Long id) {
        Optional<Transaction> txn = transactionRepository.findById(id);
        return txn.orElse(null); // or throw exception
    }

}