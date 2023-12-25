package com.app.expandapistesttask.model.dto;

import com.app.expandapistesttask.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsPayloadDTO {
    private String table;
    private Product[] records;
}
