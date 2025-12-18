package com.Management.productManagement.Repository;


import com.Management.productManagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This class to connect with the data base like queery operation dude
@Repository




public interface ProductRepository  extends JpaRepository<Product,Long> {

    // this is the custom query methods

}
