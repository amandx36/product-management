package com.Management.productManagement.service;






// here we are defining konn se kon se function hoge and their return type dude !!

import com.Management.productManagement.dto.ProductDTO;
import com.Management.productManagement.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductDTO productDTO);
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductResponseDTO> getProductsByPriceRange(Double minPrice, Double maxPrice);
    List<ProductResponseDTO> searchProducts(String keyword);
    ProductResponseDTO updateProductQuantity(Long id, Integer quantity);


}
