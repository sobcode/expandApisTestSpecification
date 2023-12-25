package com.app.expandapistesttask.service;

import com.app.expandapistesttask.model.Product;

import java.util.List;

public interface ProductService {
    void addAllProducts(Product[] products, String tableName);
    List<Product> getAllProducts();
}
