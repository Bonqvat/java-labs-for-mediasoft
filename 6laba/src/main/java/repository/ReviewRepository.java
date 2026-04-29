package com.example.restaurantapi.repository;

import com.example.restaurantapi.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {}