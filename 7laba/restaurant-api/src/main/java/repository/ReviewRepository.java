package com.example.restaurantapi.repository;

import com.example.restaurantapi.entity.Review;
import com.example.restaurantapi.entity.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий отзывов. Базовый JpaRepository предоставляет
 * пагинацию и сортировку через PagingAndSortingRepository.
 */
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
}