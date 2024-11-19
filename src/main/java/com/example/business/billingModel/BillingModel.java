package com.example.business.billingModel;

import com.example.business.salesModel.SalesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Billing")
public class BillingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sales_id", nullable = false)
    private SalesModel salesTransaction;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String paymentMethod; // e.g., CASH, CARD, ONLINE

    @Column(nullable = false)
    private LocalDateTime billingDate;

    @Column(nullable = false)
    private double totalAmount;
}

