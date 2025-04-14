package com.my.backend.dto.CartDTO;

import com.my.backend.entity.Cart;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private Long id;
    private String name;
    private int price;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;

    public static CartDTO from(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .name(cart.getName())
                .price(cart.getPrice())
                .image(cart.getImage())
                .userId(cart.getUser().getId())
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();
    }
}
