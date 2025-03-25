package com.gdc.core.payments.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "driver_bank_details",schema = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverBankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @OneToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driverId;

    @Column(name = "account_number", nullable = false, unique = true, length = 50)
    private String accountNumber;

    @Column(name = "ifsc_code", nullable = false, length = 20)
    private String ifscCode;

    @Column(name = "bank_name", nullable = false, length = 255)
    private String bankName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt ;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
