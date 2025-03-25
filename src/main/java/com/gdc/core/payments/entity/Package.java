package com.gdc.core.payments.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "packages",schema = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private Long packageId;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @Column(nullable = false,length = 100)
    private String packageType;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Boolean fragile;

}
