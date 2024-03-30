package com.techstore.mapper;

import com.techstore.dto.ProductRequestDTO;
import com.techstore.dto.ProductResponseDTO;
import com.techstore.models.ProductModel;

import java.util.List;

public interface ProductMapper {

    List<ProductResponseDTO> toResponse(List<ProductModel> productModelList);

    ProductResponseDTO toResponse(ProductModel productModel);

    ProductModel toModel(ProductRequestDTO productRequestDTO);

}
