package com.Management.productManagement.helperModule;

import com.Management.productManagement.dto.ProductResponseDTO;
import com.Management.productManagement.entity.Product;
import org.springframework.stereotype.Component;

@Component   //
public class ProductResponseDTOHelper {

    public ProductResponseDTO convertToResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getProductCode(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
