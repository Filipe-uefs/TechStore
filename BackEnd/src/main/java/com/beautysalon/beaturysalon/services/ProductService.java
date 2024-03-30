package com.beautysalon.beaturysalon.services;

import com.beautysalon.beaturysalon.dto.ProductRequestDTO;
import com.beautysalon.beaturysalon.dto.ProductResponseDTO;
import com.beautysalon.beaturysalon.mapper.ProductMapper;
import com.beautysalon.beaturysalon.models.ProductModel;
import com.beautysalon.beaturysalon.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;


    public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO) {

        ProductModel productModel = productMapper.toModel(productRequestDTO);
        ProductModel productModelInserted = productRepository.save(productModel);
        return productMapper.toResponse(productModelInserted);
    }

    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO) {

        ProductModel productModel = productMapper.toModel(productRequestDTO);
        productModel.setId(productRequestDTO.getId());
        ProductModel productModelInserted = productRepository.save(productModel);
        return productMapper.toResponse(productModelInserted);
    }

    public void deleteProduct(Long productId) {

        productRepository.deleteById(productId);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<ProductModel> productModels = productRepository.findAll();
        return productMapper.toResponse(productModels);
    }

    public ProductResponseDTO getById(Long productId) {
        Optional<ProductModel> productModel = productRepository.findById(productId);
        return productModel.map(model -> productMapper.toResponse(model)).orElse(null);

    }

}
