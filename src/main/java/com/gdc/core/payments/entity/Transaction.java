package com.gdc.core.payments.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions",schema = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(nullable = false, updatable = false)
    private String transactionReference;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private String transactionStatus;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name ="user_bank_id",nullable = false)
    private UserBankDetails userBankDetails;

    @ManyToOne
    @JoinColumn(name = "driver_bank_id",nullable = false)
    private DriverBankDetails driverBankDetails;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime timestamp;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime processedAt;

    @PrePersist
    private void onUpdate(){
        this.processedAt=LocalDateTime.now();
    }
}
