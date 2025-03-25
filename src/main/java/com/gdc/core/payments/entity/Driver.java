package com.gdc.core.payments.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "drivers",schema = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "name",nullable = false)
    @NotBlank(message = "Name is required and cannot be empty")
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 10, max = 10, message = "Contact number must be exactly 10 digits")
    @Column(name = "contact_number",nullable = false,unique = true)
    //@NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10}", message = "Contact number must contains exactly 10 digits")
    private String contactNumber;

    @Column(name = "vehicle_number",nullable = false,unique = true,length = 20)
    @NotBlank(message = "Vehicle number is required")
    @Pattern(regexp = "^[A-Z0-9-]+$",message = "Vehicle number must " +
            "contain only uppercase letters,numbers and hyphens")
    private String vehicleNumber;

    @Column(nullable = false)
    private String licenseNumber;

    @Column(name = "availability_status", nullable = false)
    private String availabilityStatus = "AVAILABLE";

    @OneToOne
    @JoinColumn(name = "driver_bank_details")
    private DriverBankDetails driverBankDetails;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    private List<Ride> ride;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
