package com.gdc.core.payments.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "payment",schema = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @OneToOne(mappedBy = "payment",cascade = CascadeType.ALL)
    private Transaction transaction;

    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false,columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double discountAmount = 0.0;

    @Column(nullable = false,columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double commissionAmount = 0.0;

    @Column(nullable = false)
    private Double checkoutamount;

    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private String dropLocation;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime processedAt;

    @PrePersist
    @PreUpdate
    public void calculateCheckoutAmount() {
        double discount= Optional.ofNullable(discountAmount).orElse(0.0);
        double commission=Optional.ofNullable(commissionAmount).orElse(0.0);
        double totalamount=Optional.ofNullable(totalAmount).orElse(0.0);

        this.checkoutamount = totalAmount - discountAmount - commissionAmount;
    }
}
