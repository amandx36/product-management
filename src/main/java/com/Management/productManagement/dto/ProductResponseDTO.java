package com.Management.productManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


//           Client ko safe, controlled data dene ke liye


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String productCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}



