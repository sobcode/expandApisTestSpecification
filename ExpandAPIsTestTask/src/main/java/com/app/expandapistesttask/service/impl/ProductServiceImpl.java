package com.app.expandapistesttask.service.impl;

import com.app.expandapistesttask.model.Product;
import com.app.expandapistesttask.repository.ProductRepository;
import com.app.expandapistesttask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void addAllProducts(Product[] products, String tableName) {
        for(Product product : products){
            product.setTableName(tableName);
            productRepository.save(product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
