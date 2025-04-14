package com.my.backend.dto.ProductDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductRequestDto {

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
}
