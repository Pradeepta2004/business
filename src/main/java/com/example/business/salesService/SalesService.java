package com.example.business.salesService;

import com.example.business.inventoryModel.InventoryModel;
import com.example.business.inventoryService.InventoryService;
import com.example.business.salesModel.SalesModel;
import com.example.business.salesRepository.SalesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalesService {
    private final SalesRepository salesRepository;
    private final InventoryService inventoryService;

    public SalesService(SalesRepository salesRepository, InventoryService inventoryService) {
        this.salesRepository = salesRepository;
        this.inventoryService = inventoryService;
    }

    @Transactional
    public SalesModel createSale(Long inventoryId, int quantity) {
        InventoryModel inventoryItem = inventoryService.getInventoryItemById(inventoryId);

        if (inventoryItem == null || quantity > inventoryItem.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock available or invalid inventory item!");
        }

        double totalPrice = quantity * inventoryItem.getPrice();
        SalesModel salesModel = new SalesModel(null, inventoryItem, quantity, totalPrice, LocalDateTime.now());

        // Update stock quantity
        inventoryItem.setQuantity(inventoryItem.getQuantity() - quantity);
        inventoryService.updateInventoryItem(inventoryId, inventoryItem);

        return salesRepository.save(salesModel);
    }

    public List<SalesModel> getSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return salesRepository.findBySaleDateBetween(startDate, endDate);
    }
}