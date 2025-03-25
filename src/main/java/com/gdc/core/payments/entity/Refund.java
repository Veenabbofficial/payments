package com.gdc.core.payments.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "refunds",schema = "payments")
@Data
@NamedEntityGraph
@AllArgsConstructor
@NamedEntityGraph(name = "Refund.transaction", attributeNodes = @NamedAttributeNode("transaction"))

public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;

    @OneToOne
    @JoinColumn(name = "transaction_id",nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 50)
    private String refundStatus;

    @Column(nullable = false,length = 50)
    private String refundMethod;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt ;

    private LocalDateTime processedAt;
    @PreUpdate
    private void onUpdate(){
        this.processedAt=LocalDateTime.now();
    }
}
