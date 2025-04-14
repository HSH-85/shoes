package com.my.backend.service;

import com.my.backend.dto.ProductDto.ProductRequestDto;
import com.my.backend.dto.ProductDto.ProductResponseDto;
import com.my.backend.entity.Product;
import com.my.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .brand(dto.getBrand())
                .price(dto.getPrice())
                .originalPrice(dto.getOriginalPrice())
                .description(dto.getDescription())
                .images(dto.getImages())
                .sizes(dto.getSizes())
                .colors(dto.getColors())
                .category(dto.getCategory())
                .tags(dto.getTags())
                .featured(dto.isFeatured())
                .inStock(dto.isInStock())
                .build();

        return convertToDto(productRepository.save(product));
    }

    public ProductResponseDto getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 상품을 찾을 수 없습니다."));
        return convertToDto(product);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto updateProduct(int id, ProductRequestDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 상품을 찾을 수 없습니다."));

        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setOriginalPrice(dto.getOriginalPrice());
        product.setDescription(dto.getDescription());
        product.setImages(dto.getImages());
        product.setSizes(dto.getSizes());
        product.setColors(dto.getColors());
        product.setCategory(dto.getCategory());
        product.setTags(dto.getTags());
        product.setFeatured(dto.isFeatured());
        product.setInStock(dto.isInStock());

        return convertToDto(productRepository.save(product));
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    private ProductResponseDto convertToDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .originalPrice(product.getOriginalPrice())
                .description(product.getDescription())
                .images(product.getImages())
                .sizes(product.getSizes())
                .colors(product.getColors())
                .category(product.getCategory())
                .tags(product.getTags())
                .featured(product.isFeatured())
                .inStock(product.isInStock())
                .build();
    }
}
