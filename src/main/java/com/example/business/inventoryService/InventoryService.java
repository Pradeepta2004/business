package com.example.business.inventoryService;

import com.example.business.exception.ResourceNotFoundException;
import com.example.business.inventoryModel.InventoryModel;
import com.example.business.inventoryRepository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    public InventoryModel createInventoryItem(InventoryModel inventoryModel) {
        validateInventoryModel(inventoryModel);
        return inventoryRepository.save(inventoryModel);
    }


    @Transactional
    public InventoryModel updateInventoryItem(Long id, InventoryModel updatedInventoryModel) {
        InventoryModel existingInventoryModel = getInventoryItemById(id);

        if (StringUtils.hasText(updatedInventoryModel.getProductName())) {
            existingInventoryModel.setProductName(updatedInventoryModel.getProductName());
        }
        if (updatedInventoryModel.getQuantity() >= 0) {
            existingInventoryModel.setQuantity(updatedInventoryModel.getQuantity());
        }
        if (updatedInventoryModel.getPrice() >= 0) {
            existingInventoryModel.setPrice(updatedInventoryModel.getPrice());
        }

        return inventoryRepository.save(existingInventoryModel);
    }


    @Transactional
    public void deleteInventoryItem(Long id) {
        InventoryModel inventoryModel = getInventoryItemById(id);
        inventoryRepository.delete(inventoryModel);
    }


    public List<InventoryModel> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }


    public InventoryModel getInventoryItemById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found with ID: " + id));
    }


    public List<InventoryModel> findItemsByProductName(String productName) {
        if (!StringUtils.hasText(productName)) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        return inventoryRepository.findByProductNameIgnoreCase(productName);
    }


    public List<InventoryModel> findLowStockItems(int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException("Threshold must be non-negative");
        }
        return inventoryRepository.findByQuantityLessThan(threshold);
    }


    public List<InventoryModel> findExpensiveItems(double minPrice) {
        if (minPrice < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
        return inventoryRepository.findByPriceGreaterThan(minPrice);
    }


    public List<InventoryModel> searchItemsByKeyword(String keyword) {
        if (!StringUtils.hasText(keyword)) {
            throw new IllegalArgumentException("Keyword cannot be empty");
        }
        return inventoryRepository.searchByProductName(keyword);
    }


    private void validateInventoryModel(InventoryModel inventoryModel) {
        if (!StringUtils.hasText(inventoryModel.getProductName())) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (inventoryModel.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        if (inventoryModel.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }
    }
}
