package com.qulron.inventory_service.Products.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private double price;
    private int quantity;

}

