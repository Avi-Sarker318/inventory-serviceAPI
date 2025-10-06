package com.qulron.inventory_service.products.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private long id;

    private String name;
    private BigDecimal price;
    private int quantity;

}

