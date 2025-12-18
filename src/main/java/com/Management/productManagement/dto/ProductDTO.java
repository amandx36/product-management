package com.Management.productManagement.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// This class for taking the data from client





// this file for the creating the product
@Data // for removing all the boiler plate code like getter and setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Description needed ")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @DecimalMin(value = "0.01", message = "Price greater than  0")
    private Double price;

    @Min(value = 0, message = "Must be positive ")
    private Integer quantity;

    @Pattern(regexp = "A-Z / a-z / 0-9", message = "Product code must follow pattern: XXX-123")
    private String productCode;
}
