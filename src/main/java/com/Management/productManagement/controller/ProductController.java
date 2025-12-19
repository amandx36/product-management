package com.Management.productManagement.controller;

import com.Management.productManagement.dto.ProductDTO;
import com.Management.productManagement.dto.ProductResponseDTO;

import com.Management.productManagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    // for calling the service layer !!

    private final ProductService productService;

    // api end point for creating product
    @PostMapping
    // @RequestBody
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        // for responding the data to fetched data from user
        ProductResponseDTO createProduct = productService.createProduct(productDTO);

        // created the product and than save it dude
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }

    // api mapping for getting the product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // to get all the product dude
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);

    }

    // for updating the product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);

    }

    // for deleteing the project dude

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product delted sucessfully ");
    }

    // get product by Prince range
    @GetMapping("/price-range")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<ProductResponseDTO> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    // get the product by search
    @GetMapping("/search")
    public  ResponseEntity<List<ProductResponseDTO>> searchProduct(@RequestParam String keyword ){
        List<ProductResponseDTO> products =  productService.searchProducts(keyword);
        return  ResponseEntity.ok(products);
    }

    // api for making the partial  updation
    @PatchMapping("/{id}/quantity")
    //   we got this from client {
    //   "quantity": 10
    //      }

    public ResponseEntity<ProductResponseDTO> updateProductQuantity(@PathVariable Long id , @RequestBody Map<String, Integer> request){
    Integer quantity = request.get("quantity");

    ProductResponseDTO updateProduct = productService.updateProductQuantity(id,quantity);
    return ResponseEntity.ok(updateProduct);
    }




    // Health Check
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Product Service is up and running!");
    }
}
