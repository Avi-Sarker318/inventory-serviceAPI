package com.qulron.inventory_service.controller;

import com.qulron.inventory_service.dto.ProductDTO;
import com.qulron.inventory_service.service.ProductService;
import com.qulron.inventory_service.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
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
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDTO dto) {
        ProductDTO savedProduct = productService.addProduct(dto);
        return ResponseEntity.ok(new ApiResponse("Product Added Successfully",savedProduct));
    }

    //READ * All products
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<ProductDTO> products = productService.getProducts();
        return ResponseEntity.ok(new ApiResponse("Products Retrieved Successfully",products));
    }
    //READ * one product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable long id) {
        ProductDTO product = productService.getProduct(id);
        return ResponseEntity.ok(new ApiResponse("Product Retrieved Successfully", product));
    }
    //UPDATE * one product by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable long id, @RequestBody ProductDTO dto) {
        ProductDTO updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(new ApiResponse("Product Updated Successfully", updated));
    }
    //DELETE * one product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted Successfully");
    }
    //Find low inventory
    @GetMapping("/low")
    //http://localhost:8000/api/products/low?threshold=3
    public ResponseEntity<List<ProductDTO>> getLowInventory(@RequestParam int threshold) {
        List<ProductDTO> lowStock = productService.getLowInventoryProducts(threshold);
        return ResponseEntity.ok(lowStock);
    }




}
