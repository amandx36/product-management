package com.Management.productManagement.entity;




// this layer class is used to for the databsae representation

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // for mapping this class to database table
@Table(name ="products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    @Id // to tell this become the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // telling to increase auto increment
    private  long id ;


    // adding the validation constraint
    @NotBlank(message = "Product name is required")
    @Size(min  = 3 , max = 500 , message = "The name of product must be in between 3 ro 500")
    @Column(nullable = false)
    private  String name ;

    // adding the validation constraint
    @NotBlank(message = "Product name is required")
    @Size(min  = 3 , max = 500 , message = "The name of product must be in between 3 ro 500")
    @Column(nullable = false)
    private  String description  ;


    @DecimalMin(value =  "0,01" , message = "Price must be greater than 0 ")
    @Column(nullable = false)
    private Double price ;


    // for the quantity

    @Min(value =  0 , message = "Value cannot be negative ")
    @Column(nullable = false)
    private  Integer quantity ;


    // for the product code

    @Pattern(regexp = "A-Z a-z 0-9", message = "Should be follow the code ")
    @Column(nullable = false)
    private String productCode ;


    // for column for creating and updating timeZone
    @Column(name = "created at ")
    private LocalDateTime updatedAt ;

    @Column (name = "updated at ")
    private LocalDateTime createdAt ;


    @PrePersist    // run this function  before entity saves the attributes into the database
    protected  void onCreate(){
        createdAt  = LocalDateTime.now() ;
        updatedAt = LocalDateTime.now();

    }

    @PreUpdate  // run this  fucntion before there is  updation to the database happens dude
    protected  void onUpdated(){
        updatedAt = LocalDateTime.now();

    }




}
