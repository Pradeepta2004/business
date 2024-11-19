package com.example.business.salesController;

import com.example.business.salesModel.SalesModel;
import com.example.business.salesService.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/add")
    public ResponseEntity<SalesModel> createSale(@RequestParam Long inventoryId, @RequestParam int quantity) {
        SalesModel salesModel = salesService.createSale(inventoryId, quantity);
        return ResponseEntity.ok(salesModel);
    }

    @GetMapping
    public ResponseEntity<List<SalesModel>> getSalesBetweenDates(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(salesService.getSalesBetweenDates(start, end));
    }
}