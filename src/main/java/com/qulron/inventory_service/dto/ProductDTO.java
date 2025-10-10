package com.qulron.inventory_service.dto;

import lombok.*;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private long id;

    @NotNull(message = "name should not be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "price cannot be null")
    @NotBlank(message = "price cannot be empty")
    private BigDecimal price;
    
    private int quantity;

}

