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
    @Min(value = 0, message = "price cannot be less than 0")
    private BigDecimal price;

    @Min(value = 0, message = "quantity cannot be less than 0")
    private int quantity;

}

