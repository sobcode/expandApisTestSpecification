package com.app.expandapistesttask.repository;

import com.app.expandapistesttask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
