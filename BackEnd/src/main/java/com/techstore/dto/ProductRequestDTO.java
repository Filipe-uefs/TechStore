package com.techstore.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private Long id;
    private String name;
    private BigDecimal unitPrice;
    private int quantity;

}
