package com.tanabesports.inventory.service;

import com.tanabesports.inventory.model.InventoryMovement;
import com.tanabesports.inventory.model.Product;
import com.tanabesports.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Product updateStock(String name, int newStock) {
        String status = "DISPONIBLE";
        if (newStock == 0) {
            status = "AGOTADO";
        } else if (newStock <= 3) {
            status = "STOCK_BAJO";
        }

        Product product = productRepository.findByName(name)
                .orElse(new Product(name, newStock, status));
        
        product.setStock(newStock);
        product.setStatus(status);
        productRepository.save(product);

        // DISPARAR EVENTO (Patrón Observer)
        eventPublisher.publishEvent(new InventoryMovement(name, newStock, status));

        return product;
    }

    // --- OBSERVADORES CONCRETOS (Listeners) ---

    @EventListener
    public void handleWarehouseNotification(InventoryMovement event) {
        System.out.println("[ALMACÉN] Preparando reposición para el producto: '" + event.getProductName() + 
                           "' | Estado: " + event.getStatus() + " | Stock: " + event.getStock());
    }

    @EventListener
    public void handleSalesNotification(InventoryMovement event) {
        if ("AGOTADO".equals(event.getStatus())) {
            System.out.println("[VENTAS] ¡Alerta! '" + event.getProductName() + "' agotado. Actualizando tienda online.");
        } else if ("STOCK_BAJO".equals(event.getStatus())) {
            System.out.println("[VENTAS] Aviso: Stock bajo en '" + event.getProductName() + "'.");
        }
    }

    @EventListener
    public void handleManagerAudit(InventoryMovement event) {
        System.out.println("[GERENCIA] Movimiento registrado en auditoría para: " + event.getProductName());
    }
}
