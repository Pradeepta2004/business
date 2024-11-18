package com.example.business.inventoryController;

import com.example.business.inventoryModel.InventoryModel;
import com.example.business.inventoryService.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Create a new inventory item
    @PostMapping
    public ResponseEntity<InventoryModel> createInventoryItem(@RequestBody InventoryModel inventoryModel) {
        InventoryModel createdInventoryModel = inventoryService.createInventoryItem(inventoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventoryModel);
    }

    // Update an existing inventory item
    @PutMapping("/{id}")
    public ResponseEntity<InventoryModel> updateInventoryItem(@PathVariable Long id, @RequestBody InventoryModel inventoryModel) {
        InventoryModel updatedInventoryModel = inventoryService.updateInventoryItem(id, inventoryModel);
        return ResponseEntity.ok(updatedInventoryModel);
    }

    // Delete an inventory item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return ResponseEntity.noContent().build();
    }

    // Get all inventory items
    @GetMapping
    public ResponseEntity<List<InventoryModel>> getAllInventoryItems() {
        List<InventoryModel> inventoryItems = inventoryService.getAllInventoryItems();
        return ResponseEntity.ok(inventoryItems);
    }

    // Get a specific inventory item by ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryModel> getInventoryItemById(@PathVariable Long id) {
        InventoryModel inventoryModel = inventoryService.getInventoryItemById(id);
        return ResponseEntity.ok(inventoryModel);
    }

    // Search inventory items by product name
    @GetMapping("/search")
    public ResponseEntity<List<InventoryModel>> searchInventory(@RequestParam String keyword) {
        List<InventoryModel> searchResults = inventoryService.searchItemsByKeyword(keyword);
        return ResponseEntity.ok(searchResults);
    }

    // Find inventory items by product name
    @GetMapping("/findByName")
    public ResponseEntity<List<InventoryModel>> findByProductName(@RequestParam String productName) {
        List<InventoryModel> items = inventoryService.findItemsByProductName(productName);
        return ResponseEntity.ok(items);
    }

    // Find low stock items
    @GetMapping("/lowStock")
    public ResponseEntity<List<InventoryModel>> findLowStockItems(@RequestParam int threshold) {
        List<InventoryModel> lowStockItems = inventoryService.findLowStockItems(threshold);
        return ResponseEntity.ok(lowStockItems);
    }

    // Find expensive items
    @GetMapping("/expensiveItems")
    public ResponseEntity<List<InventoryModel>> findExpensiveItems(@RequestParam double minPrice) {
        List<InventoryModel> expensiveItems = inventoryService.findExpensiveItems(minPrice);
        return ResponseEntity.ok(expensiveItems);
    }
}
