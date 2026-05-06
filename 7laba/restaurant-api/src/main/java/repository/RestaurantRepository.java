package com.example.restaurantapi.repository;

import com.example.restaurantapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Репозиторий ресторанов.
 * Добавлены два метода поиска по минимальному рейтингу (Требование 3).
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Способ 1: Spring Data сам сгенерирует запрос по конвенции имени метода
    List<Restaurant> findByRatingGreaterThanEqual(double minRating);

    // Способ 2: Явный JPQL запрос с аннотацией @Query
    @Query("SELECT r FROM Restaurant r WHERE r.rating >= :minRating")
    List<Restaurant> findRestaurantsWithMinRating(@Param("minRating") double minRating);
}