package com.my.backend.service;

import com.my.backend.dto.CartDTO.CartDTO;
import com.my.backend.entity.Cart;
import com.my.backend.entity.User;
import com.my.backend.repository.CartRepository;
import com.my.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    //    장바구니 추가
    public CartDTO addCart(CartDTO cartDTO) {
        User user = userRepository.findById(cartDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Cart cart = Cart.builder()
                .name(cartDTO.getName())
                .price(cartDTO.getPrice())
                .image(cartDTO.getImage())
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return CartDTO.from(cartRepository.save(cart));
    }

    // 장바구니 조회
    public List<CartDTO> getCartProduct(Long userId) {
        return cartRepository.findByUserId(userId).stream()
                .map(CartDTO::from)
                .collect(Collectors.toList());
    }

    // 장바구니 삭제
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
