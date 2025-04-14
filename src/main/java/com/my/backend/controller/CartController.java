package com.my.backend.controller;

import com.my.backend.dto.CartDTO.CartDTO;
import com.my.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    //   장바구니 등록
    @PostMapping
    public ResponseEntity<CartDTO> addCart(@RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(cartService.addCart(cartDTO));
    }

    //    장바구니 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartDTO>> getCartProduct(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartProduct(userId));
    }

    //    장바구니 삭제
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
