package com.example.business.billingController;

import com.example.business.billingModel.BillingModel;
import com.example.business.billingService.BillingService;
import com.example.business.salesModel.SalesModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping
    public ResponseEntity<BillingModel> generateBill(@RequestParam Long salesId, @RequestParam String customerName, @RequestParam String paymentMethod) {
        // Fetch SalesModel by id
        SalesModel salesTransaction = billingService.findSalesById(salesId);
        if (salesTransaction == null) {
            return ResponseEntity.notFound().build(); // Return 404 if sales transaction not found
        }

        // Generate bill
        BillingModel billingModel = billingService.generateBill(salesTransaction, customerName, paymentMethod);
        return ResponseEntity.ok(billingModel);
    }

    @GetMapping
    public ResponseEntity<List<BillingModel>> findBillsByCustomer(@RequestParam String customerName) {
        return ResponseEntity.ok(billingService.findBillsByCustomer(customerName));
    }
}
