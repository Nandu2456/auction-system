package com.bank.kafka.transaction_pipeline.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senderAccount;
    private String receiverAccount;
    private Double amount;
    private String status;
    private LocalDateTime timestamp;

    public double getBidAmount() {
    return amount;
}

}