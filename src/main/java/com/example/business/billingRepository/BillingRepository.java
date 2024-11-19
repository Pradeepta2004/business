package com.example.business.billingRepository;

import com.example.business.billingModel.BillingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<BillingModel, Long> {
    List<BillingModel> findByCustomerName(String customerName);
}