package com.my.backend.repository;

import com.my.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // 기본적인 CRUD는 JpaRepository로 자동 제공됨
}
