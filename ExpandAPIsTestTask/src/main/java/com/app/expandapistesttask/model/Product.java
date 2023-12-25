package com.app.expandapistesttask.model;

import com.app.expandapistesttask.controller.UserController;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "entryDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate entryDate;
    @Column(name = "itemCode")
    private int itemCode;
    @Column(name = "itemName")
    private String itemName;
    @Column(name = "itemQuantity")
    private int itemQuantity;
    @Column(name = "status")
    private String status;



    public Product(LocalDate entryDate, int itemCode, String itemName, int itemQuantity, String status) {
        this.entryDate = entryDate;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.status = status;
    }

    public void setTableName(String tableName) {
        Logger logger = LoggerFactory.getLogger(UserController.class);

        Table tableAnnotation = getClass().getAnnotation(Table.class);
        if (tableAnnotation != null) {
            try {
                Field nameField = tableAnnotation.getClass().getDeclaredField("name");
                nameField.setAccessible(true);
                nameField.set(tableAnnotation, tableName);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.warn("Something wrong with naming the products table. Default name: 'products'");
            }
        }
    }
}
