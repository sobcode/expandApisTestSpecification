package com.app.expandapistesttask.producttests;

import com.app.expandapistesttask.model.Product;
import com.app.expandapistesttask.model.User;
import com.app.expandapistesttask.repository.ProductRepository;
import com.app.expandapistesttask.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class ProductServiceIT {
    private ProductService productService;
    private Product product1, product2;
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceIT(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        product1 = new Product();
        product1.setEntryDate(LocalDate.now());
        product1.setItemCode(11211);
        product1.setItemName("Test Inventory 5");
        product1.setItemQuantity(3);
        product1.setStatus("Processing");

        product2 = new Product();
        product2.setEntryDate(LocalDate.now());
        product2.setItemCode(11311);
        product2.setItemName("Test Inventory 8");
        product2.setItemQuantity(7);
        product2.setStatus("Paid");
    }

    @AfterEach
    public void cleanData(){
        productRepository.deleteAll();
    }

    @Test
    public void whenSaveAndRetrieveEntity_thenOK() {
        List<Product> afterSaveProducts = productService.addAllProducts(new Product[]{product1, product2},
                "products");
        List<Product> retrievedProducts = productService.getAllProducts();

        Assertions.assertNotEquals(0, retrievedProducts.get(0).getId());
        Assertions.assertNotEquals(0, retrievedProducts.get(1).getId());
        Assertions.assertNotNull(retrievedProducts.get(0));
        Assertions.assertNotNull(retrievedProducts.get(1));
        Assertions.assertEquals(2, retrievedProducts.size());
    }
}
