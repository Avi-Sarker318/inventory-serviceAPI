package com.qulron.inventory_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data   //generates Getter, Setter, equals, hashcode, toString
@NoArgsConstructor //generates empty constructor
@AllArgsConstructor //generates constructor with field
@Builder
/**Product p1 = Product.Builder()
        .name("Macbook")
        .price(999.99)
        .quantity(11)
        .build()
**/
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2 )
    private BigDecimal price;

    private int quantity;
}
