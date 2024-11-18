package com.example.business.inventoryService;

import com.example.business.inventoryModel.InventoryModel;
import com.example.business.inventoryRepository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Create a new inventory item
    public InventoryModel createInventoryItem(InventoryModel inventoryModel) {
        return inventoryRepository.save(inventoryModel);
    }

    // Update an existing inventory item
    public InventoryModel updateInventoryItem(Long id, InventoryModel updatedInventoryModel) {
        InventoryModel existingInventoryModel = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item not found with ID: " + id));

        existingInventoryModel.setProductName(updatedInventoryModel.getProductName());
        existingInventoryModel.setQuantity(updatedInventoryModel.getQuantity());
        existingInventoryModel.setPrice(updatedInventoryModel.getPrice());

        return inventoryRepository.save(existingInventoryModel);
    }

    // Delete an inventory item by ID
    public void deleteInventoryItem(Long id) {
        inventoryRepository.deleteById(id);
    }

    // Get all inventory items
    public List<InventoryModel> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    // Get a specific inventory item by ID
    public InventoryModel getInventoryItemById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item not found with ID: " + id));
    }

    // Find inventory items by product name
    public List<InventoryModel> findItemsByProductName(String productName) {
        return inventoryRepository.findByProductNameIgnoreCase(productName);
    }

    // Find inventory items with low stock
    public List<InventoryModel> findLowStockItems(int threshold) {
        return inventoryRepository.findByQuantityLessThan(threshold);
    }

    // Find expensive inventory items
    public List<InventoryModel> findExpensiveItems(double minPrice) {
        return inventoryRepository.findByPriceGreaterThan(minPrice);
    }

    // Search inventory items by keyword
    public List<InventoryModel> searchItemsByKeyword(String keyword) {
        return inventoryRepository.searchByProductName(keyword);
    }
}
