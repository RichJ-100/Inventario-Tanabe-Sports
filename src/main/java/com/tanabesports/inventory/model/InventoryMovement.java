package com.tanabesports.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "inventory_movements")
public class InventoryMovement {
    @Id
    private String id;
    private String productName;
    private int stock;
    private String status;
    private LocalDateTime timestamp;

    public InventoryMovement(String productName, int stock, String status) {
        this.productName = productName;
        this.stock = stock;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getProductName() { return productName; }
    public int getStock() { return stock; }
    public String getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
