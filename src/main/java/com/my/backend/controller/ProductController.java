package com.my.backend.controller;

import com.my.backend.dto.ProductDto.ProductRequestDto;
import com.my.backend.dto.ProductDto.ProductResponseDto;
import com.my.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 상품 등록
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto dto) {
        ProductResponseDto response = productService.createProduct(dto);
        return ResponseEntity.ok(response);
    }

    // 단일 상품 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable int id) {
        ProductResponseDto response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    // 전체 상품 조회
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> responseList = productService.getAllProducts();
        return ResponseEntity.ok(responseList);
    }

    // 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable int id,
            @RequestBody ProductRequestDto dto
    ) {
        ProductResponseDto response = productService.updateProduct(id, dto);
        return ResponseEntity.ok(response);
    }

    // 상품 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
