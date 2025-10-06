package com.qulron.inventory_service.products.controller;

import com.qulron.inventory_service.products.dto.ProductDTO;
import com.qulron.inventory_service.products.entity.Product;
import com.qulron.inventory_service.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    /**
     * External Links helped me find this idea
     * https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
     * https://codesarray.com/view/Data-Transfer-Object-(DTO)-in-Spring-boot
     */

    private final ProductService productService;

    //CREATE a new product
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto) {
        ProductDTO savedProduct = productService.addProduct(dto);
        return ResponseEntity.ok(savedProduct);
    }

    //READ * All products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }
    //READ * one product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }
    //UPDATE * one product by ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable long id, @RequestBody ProductDTO dto) {
        ProductDTO updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updated);
    }
    //DELETE * one product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Student deleted Successfully");
    }




}
