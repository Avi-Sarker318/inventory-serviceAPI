package com.qulron.inventory_service.Products.service;

import com.qulron.inventory_service.Products.dto.ProductDTO;
import com.qulron.inventory_service.Products.entity.Product;
import com.qulron.inventory_service.Products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;

@Slf4j //just a logger
@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    //get product by id
    public ProductDTO getProduct(long id) {
        log.info("fetching product Id");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product id not found"));
        return new ProductDTO(product.getName(), product.getQuantity(), product.getQuantity());
    }
    public ProductDTO addProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        Product saved = productRepository.save(product);

        return new ProductDTO(saved.getName(),saved.getPrice(),saved.getQuantity());

    }



}
