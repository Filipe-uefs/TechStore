package com.techstore.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "PRODUCT")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal unitPrice;

    private int quantity;

}
