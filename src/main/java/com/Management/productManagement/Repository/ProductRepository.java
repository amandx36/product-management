package com.Management.productManagement.Repository;

import com.Management.productManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// This class to connect with the data base like queery operation dude
@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {

    // this is the custom query methods
    Optional<Product> findByName(String name);


    Optional<Product> findByProductCode(String productCode);

    List<Product> findByPriceGreaterThan(Double price);

    List<Product> findByQuantityLessThan(Integer quantity);

    // writing my own query for our database !!
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findProductByPriceRange(
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice);

    // my own query for finding the product in the data base
    @Query("""
                SELECT p
                FROM Product p
                WHERE LOWER(p.name) LIKE %:keyword%
                   OR LOWER(p.description) LIKE %:keyword%
            """)
    List<Product> searchProducts(@Param("keyword") String keyword);

}
