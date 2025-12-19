package com.Management.productManagement.service;

import com.Management.productManagement.Repository.ProductRepository;
import com.Management.productManagement.dto.ProductDTO;
import com.Management.productManagement.dto.ProductResponseDTO;
import com.Management.productManagement.entity.Product;
import com.Management.productManagement.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.Management.productManagement.helperModule.ProductResponseDTOHelper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // for connecting to the datbase through productRepository ;
    private final ProductRepository productRepository;

    // for using the function converting the Entity to dto for returning the desire
    // detail

    private final ProductResponseDTOHelper productResponseDTOHelper; // inject






    @Override
    public ProductResponseDTO createProduct(ProductDTO productDTO) {

        // check is product name already exist
        if (productRepository.findByName(productDTO.getName()).isPresent()) {
            throw new ResourceNotFoundException("product with name " + productDTO.getName() + "already exist");

        }
        // check is the product code is already exist or not
        if (productRepository.findByProductCode(productDTO.getProductCode()).isPresent()) {
            throw new IllegalArgumentException(
                    "Product with code " + productDTO.getProductCode() + " already exists");
        }

        // now convert the dto to the entity so that we can save into the database !!
        // converting dto to entity !!
        Product product = new Product();
        // here is all the work of the setter !!

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setProductCode(productDTO.getProductCode());

        // save to the data base
        Product savedProduct = productRepository.save(product);

        // for returning convert the entity to the dto brother
        // return convertToResponseDTO(savedProduct);
        return productResponseDTOHelper.convertToResponseDTO(savedProduct);
    }




    // function to get product by id
    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        return productResponseDTOHelper.convertToResponseDTO(product);
    }

    // function for getting all the products !!
    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponseDTO> responseList = new ArrayList<>();
    // for converting the Entity to dto for sending the response to the user !!!

        for (Product product : products) {
            ProductResponseDTO dto = productResponseDTOHelper.convertToResponseDTO(product);
            responseList.add(dto);
        }

        return responseList;
    }

    // fucntion for updating the product
    @Override
    public ProductResponseDTO updateProduct(Long id , ProductDTO productDTO){
        // product ko find karo if not found raise the exception !!

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));


        // now update the value
        //
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setProductCode(productDTO.getProductCode());

        Product updatedProduct = productRepository.save(product);

        return productResponseDTOHelper.convertToResponseDTO(updatedProduct);
    }

    // fucntion for deleting the data
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        productRepository.delete(product);
    }

    // function for getting the  product by price range


    @Override
    public List<ProductResponseDTO> getProductsByPriceRange(Double minPrice, Double maxPrice) {

        List<Product> products =
                productRepository.findProductByPriceRange(minPrice, maxPrice);

        List<ProductResponseDTO> responseList = new ArrayList<>();

        for (Product product : products) {
            ProductResponseDTO dto = productResponseDTOHelper.convertToResponseDTO(product);
            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public List<ProductResponseDTO> searchProducts(String keyword) {

        List<Product> products = productRepository.searchProducts(keyword);

        List<ProductResponseDTO> responseList = new ArrayList<>();

        for (Product product : products) {
            ProductResponseDTO dto = productResponseDTOHelper.convertToResponseDTO(product);
            responseList.add(dto);
        }

        return responseList;
    }

//    DB se products mile
//    Empty response list banayi
//    Ek-ek product uthaya
//    DTO me convert kiya
//    List me add kiya
//    List return to controller !! 



    @Override
    public ProductResponseDTO updateProductQuantity(Long id, Integer quantity) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "id", id)
                );

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        product.setQuantity(quantity);

        Product updatedProduct = productRepository.save(product);

        return productResponseDTOHelper.convertToResponseDTO(updatedProduct);
    }

}
