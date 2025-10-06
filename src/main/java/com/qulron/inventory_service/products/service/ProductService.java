package com.qulron.inventory_service.products.service;

import com.qulron.inventory_service.products.dto.ProductDTO;
import com.qulron.inventory_service.products.entity.Product;
import com.qulron.inventory_service.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j //just a logger
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    //* ADD
    public ProductDTO addProduct(ProductDTO dto) {
        log.info("Adding product: {}", dto.getName());
        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
        Product saved = productRepository.save(product);
        return mapToDTO(saved);
    }
    //* GET ALL
    public List<Product> getProducts() {
        log.info("Fetching all Products...");
        return productRepository.findAll();

    }
    //* GET PRODUCT
    public ProductDTO getProduct(long id) {
        log.info("Fetching a product by id");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found by id"+id));
        return mapToDTO(product);


    }
    // * UPDATE
    public ProductDTO updateProduct(long id,ProductDTO dto) {
        log.info("Updating student with ID {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id"+id));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product updated = productRepository.save(product);
        return mapToDTO(updated);
    }

    //* DELETE
    public void deleteProduct(long id) {
        log.info("Deleting product with Id");
        if(!productRepository.existsById(id)) {
            throw new RuntimeException("product not found with Id");
        }
        productRepository.deleteById(id);
    }

    //* UTILITY
    private ProductDTO mapToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

}
