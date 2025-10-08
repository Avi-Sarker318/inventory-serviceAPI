package com.qulron.inventory_service.products.repository;

import com.qulron.inventory_service.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //low quantity
    List<Product> findByQuantityLessThan(int threshold);
}
