package com.techstore.mapper.impl;

import com.techstore.dto.ProductRequestDTO;
import com.techstore.dto.ProductResponseDTO;
import com.techstore.mapper.ProductMapper;
import com.techstore.models.ProductModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public List<ProductResponseDTO> toResponse(List<ProductModel> productModelList) {
        if (productModelList == null) {
            return null;
        }

        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (ProductModel productModel: productModelList) {
            productResponseDTOS.add(toResponse(productModel));
        }

        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO toResponse(ProductModel productModel) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(productModel.getId());
        productResponseDTO.setName(productModel.getName());
        productResponseDTO.setQuantity(productModel.getQuantity());
        productResponseDTO.setUnitPrice(productModel.getUnitPrice());
        return productResponseDTO;
    }

    @Override
    public ProductModel toModel(ProductRequestDTO productRequestDTO) {
        ProductModel productModel = new ProductModel();
        productModel.setName(productRequestDTO.getName());
        productModel.setQuantity(productRequestDTO.getQuantity());
        productModel.setUnitPrice(productRequestDTO.getUnitPrice());
        return productModel;
    }

}
