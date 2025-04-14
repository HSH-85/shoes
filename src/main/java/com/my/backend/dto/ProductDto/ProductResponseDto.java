package com.my.backend.dto.ProductDto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ProductResponseDto {

    private int id;
    private String name;
    private String brand;
    private double price;
    private Double originalPrice;
    private String description;

    private List<String> images;
    private List<Double> sizes;
    private List<String> colors;
    private String category;
    private List<String> tags;

    private boolean featured;
    private boolean inStock;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
