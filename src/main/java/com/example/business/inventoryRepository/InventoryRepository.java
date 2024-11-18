package com.example.business.inventoryRepository;

import com.example.business.inventoryModel.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, Long> {

    // Find inventory items by product name (case-insensitive)
    List<InventoryModel> findByProductNameIgnoreCase(String productName);

    // Find inventory items with quantity less than a specified value
    List<InventoryModel> findByQuantityLessThan(int quantity);

    // Find inventory items with price greater than a specified value
    List<InventoryModel> findByPriceGreaterThan(double price);

    // Search inventory items containing a keyword in the product name
    @Query("SELECT i FROM InventoryModel i WHERE LOWER(i.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<InventoryModel> searchByProductName(@Param("keyword") String keyword);
}
