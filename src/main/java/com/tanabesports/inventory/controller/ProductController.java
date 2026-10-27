package com.tanabesports.inventory.controller;

import com.tanabesports.inventory.model.Product;
import com.tanabesports.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/stock")
    public ResponseEntity<Product> updateStock(@RequestParam String name, @RequestParam int stock) {
        Product updatedProduct = productService.updateStock(name, stock);
        return ResponseEntity.ok(updatedProduct);
    }
}
