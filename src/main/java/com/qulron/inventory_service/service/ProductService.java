package com.qulron.inventory_service.service;

import com.qulron.inventory_service.dto.ProductDTO;
import com.qulron.inventory_service.entity.Product;
import com.qulron.inventory_service.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Slf4j //just a logger
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    //* ADD
    public ProductDTO addProduct(ProductDTO dto) {
        //validation
        if(dto.getPrice().compareTo(BigDecimal.ZERO)<0) {
            throw new IllegalArgumentException("Price Cannot Be Less Than 0");
        }
        if(dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new EntityNotFoundException("Name Cannot Be Empty");
        }

        log.info("Adding product: {}", dto.getName().trim());
        Product product = Product.builder()
                .name(dto.getName().trim())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
        Product saved = productRepository.save(product);
        return mapToDTO(saved);
    }
    //* GET ALL
    public List<ProductDTO> getProducts() {
        log.info("Fetching all Products...");
        List<Product> products = productRepository.findAll();

        if(products.isEmpty()) {
            throw new EntityNotFoundException("No Products found in the database");
        }

        return products.stream().map(this::mapToDTO).toList();

    }
    //* GET PRODUCT
    public ProductDTO getProduct(long id) {
        log.info("Fetching a product by id");
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found by id"+id));
        return mapToDTO(product);


    }
    // * UPDATE
    public ProductDTO updateProduct(long id,ProductDTO dto) {
        log.info("Updating student with ID {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id"+id));

        if(dto.getPrice().compareTo(BigDecimal.ZERO)<0) {
            throw new IllegalArgumentException("Price Cannot Be Less Than 0");
        }
        if(dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new EntityNotFoundException("Name Cannot Be Empty");
        }

        product.setName(dto.getName().trim());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Product updated = productRepository.save(product);
        return mapToDTO(updated);
    }

    //* DELETE
    public void deleteProduct(long id) {
        log.info("Deleting product with Id");
        if(!productRepository.existsById(id)) {
            throw new EntityNotFoundException("product not found with Id");
        }
        productRepository.deleteById(id);
    }

    //finding lowest inventory of products
    public List<ProductDTO> getLowInventoryProducts(int threshold) {
        return productRepository.findByQuantityLessThan(threshold)
                .stream()
                .map(this::mapToDTO)
                .toList();
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
