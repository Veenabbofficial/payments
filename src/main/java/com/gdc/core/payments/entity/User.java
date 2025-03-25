package com.gdc.core.payments.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users",schema = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name",nullable = false)
    @NotBlank(message = "Name is required and cannot be empty")
    private String name;

    @Column(name = "age",nullable = false)
    private Integer age;

    @Column(name = "gender",nullable = false)
    @NotBlank(message = "Gender is required")
    private String gender;

    @Column(unique = true,nullable = false)
    private String email;

    private String password;

    @Column(name = "contact_number",nullable = false,unique = true,length = 10)
    @NotBlank(message = "Contact number cannot be empty")
    @Pattern(regexp = "\\d{10}", message = "Contact number must contain exactly 10 digits")
    private String contactNumber;

    @Column(nullable = false)
    private String address;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserBankDetails userBankDetails;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders;

    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime processedAt;
    @PrePersist
    protected void onCreate(){
        createdAt=LocalDateTime.now();
    }
}
