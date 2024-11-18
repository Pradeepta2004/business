package com.example.business.inventoryModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Inventory")
public class InventoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Product name cannot be empty")
    private String productName;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @Min(value = 0, message = "Price must be 0 or greater")
    private double price;
}
