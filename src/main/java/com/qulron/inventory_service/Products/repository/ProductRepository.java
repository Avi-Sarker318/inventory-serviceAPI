package com.qulron.inventory_service.Products.repository;

import com.qulron.inventory_service.Products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
