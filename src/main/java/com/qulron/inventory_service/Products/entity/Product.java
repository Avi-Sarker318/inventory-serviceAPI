package com.qulron.inventory_service.Products.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data   //generates Getter, Setter, equals, hashcode, toString
@NoArgsConstructor //generates empty constructor
@AllArgsConstructor //generates constructor with field
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //Do I need to do column(nullable = false, length = 50 for this? is it best practice?
    //Even if it will match with the database anyways?
    private String name;
    private double price;
    private int quantity;
}
