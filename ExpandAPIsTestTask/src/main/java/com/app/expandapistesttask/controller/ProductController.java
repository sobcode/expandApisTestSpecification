package com.app.expandapistesttask.controller;

import com.app.expandapistesttask.model.Product;
import com.app.expandapistesttask.model.dto.ProductsPayloadDTO;
import com.app.expandapistesttask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public void addProducts(@RequestBody ProductsPayloadDTO productsPayloadDTO){
      productService.addAllProducts(productsPayloadDTO.getRecords(), productsPayloadDTO.getTable());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
