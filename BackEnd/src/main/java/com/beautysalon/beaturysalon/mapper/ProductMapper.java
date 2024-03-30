package com.beautysalon.beaturysalon.mapper;

import com.beautysalon.beaturysalon.dto.ProductRequestDTO;
import com.beautysalon.beaturysalon.dto.ProductResponseDTO;
import com.beautysalon.beaturysalon.models.ProductModel;

import java.util.List;

public interface ProductMapper {

    List<ProductResponseDTO> toResponse(List<ProductModel> productModelList);

    ProductResponseDTO toResponse(ProductModel productModel);

    ProductModel toModel(ProductRequestDTO productRequestDTO);

}
