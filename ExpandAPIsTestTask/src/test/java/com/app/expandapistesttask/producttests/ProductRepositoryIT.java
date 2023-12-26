package com.app.expandapistesttask.producttests;

import com.app.expandapistesttask.model.Product;
import com.app.expandapistesttask.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ProductRepositoryIT {
    private ProductRepository productRepository;
    private Product product;

    @Autowired
    public ProductRepositoryIT(ProductRepository productRepository) {
        this.productRepository = productRepository;
        product = new Product();
        product.setEntryDate(LocalDate.now());
        product.setItemCode(11211);
        product.setItemName("Test Inventory 5");
        product.setItemQuantity(3);
        product.setStatus("Processing");
    }

    @AfterEach
    public void cleanData(){
        productRepository.deleteAll();
    }

    @Test
    public void whenSaveAndRetrieveEntity_thenOK() {
        Product afterSaveProduct = productRepository.save(product);
        Product retrievedProduct = productRepository.findById(afterSaveProduct.getId()).get();

        Assertions.assertNotEquals(0, afterSaveProduct.getId());
        Assertions.assertNotNull(retrievedProduct);
        Assertions.assertEquals(product.getItemName(), retrievedProduct.getItemName());
    }
}
