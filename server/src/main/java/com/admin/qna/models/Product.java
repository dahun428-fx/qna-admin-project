package com.admin.qna.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pCode;
    private String productCode;
    private String productName;
    private String brandCode;
    private String brandName;
    private String categoryCode;
    private String categoryName;
}
