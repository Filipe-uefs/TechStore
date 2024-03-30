package com.techstore.controllers;

import com.techstore.dto.ProductRequestDTO;
import com.techstore.dto.ProductResponseDTO;
import com.techstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/product")
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

        List<ProductResponseDTO> productResponseDTOS = productService.getAllProducts();

        return new ResponseEntity<>(productResponseDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long productId) {

        ProductResponseDTO productResponseDTO = productService.getById(productId);

        if (productResponseDTO != null) {
            return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long productId) {

        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequestDTO productRequestDTO) {

        if (productRequestDTO.getName().isBlank()) {
            return new ResponseEntity<>("Nome do produto não pode ser vazio", HttpStatus.BAD_REQUEST);
        }
        ProductResponseDTO productResponseDTO = productService.saveProduct(productRequestDTO);

        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequestDTO productRequestDTO) {

        if (productRequestDTO.getName().isBlank()) {
            return new ResponseEntity<>("Nome do produto não pode ser vazio", HttpStatus.BAD_REQUEST);
        }

        ProductResponseDTO productResponseDTO = productService.updateProduct(productRequestDTO);

        return new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
    }

}
