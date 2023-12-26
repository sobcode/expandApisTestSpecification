package com.app.expandapistesttask.service.impl;

import com.app.expandapistesttask.model.Product;
import com.app.expandapistesttask.repository.ProductRepository;
import com.app.expandapistesttask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> addAllProducts(Product[] products, String tableName) {
        List<Product> productlist = new ArrayList<>();
        for(Product product : products){
            product.setTableName(tableName);
            productlist.add(productRepository.save(product));
        }

        return productlist;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
