package com.example.business.billingService;

import com.example.business.billingModel.BillingModel;
import com.example.business.billingRepository.BillingRepository;
import com.example.business.salesModel.SalesModel;
import com.example.business.salesRepository.SalesRepository; // Make sure this is injected
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingService {

    private final BillingRepository billingRepository;
    private final SalesRepository salesRepository; // Inject SalesRepository

    public BillingService(BillingRepository billingRepository, SalesRepository salesRepository) {
        this.billingRepository = billingRepository;
        this.salesRepository = salesRepository;
    }

    public BillingModel generateBill(SalesModel salesTransaction, String customerName, String paymentMethod) {
        // Create a new BillingModel
        BillingModel billingModel = new BillingModel(
                null,
                salesTransaction,
                customerName,
                paymentMethod,
                LocalDateTime.now(),
                salesTransaction.getTotalPrice()
        );
        return billingRepository.save(billingModel);
    }

    public List<BillingModel> findBillsByCustomer(String customerName) {
        return billingRepository.findByCustomerName(customerName);
    }

    public SalesModel findSalesById(Long salesId) {
        return salesRepository.findById(salesId).orElse(null); // Fetch the sale by its id
    }
}