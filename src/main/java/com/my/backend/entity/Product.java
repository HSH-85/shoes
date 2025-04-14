package com.my.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    private String brand;

    private double price;

    private Double originalPrice; // null 허용

    @Column(length = 2000)
    private String description;

    @ElementCollection
    private List<String> images;

    @ElementCollection
    private List<Double> sizes;

    @ElementCollection
    private List<String> colors;

    private String category;

    @ElementCollection
    private List<String> tags;

    private boolean featured;

    private boolean inStock;
}
