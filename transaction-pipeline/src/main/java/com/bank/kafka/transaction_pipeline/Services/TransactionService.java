package com.bank.kafka.transaction_pipeline.Services;

import com.bank.kafka.transaction_pipeline.model.Transaction;
import java.util.*;

public interface TransactionService
{
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);


}