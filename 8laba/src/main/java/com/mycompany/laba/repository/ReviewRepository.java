package com.mycompany.laba.repository;

import com.mycompany.laba.model.Review;
import com.mycompany.laba.model.User;
import com.mycompany.laba.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurantId(Long restaurantId);
    List<Review> findByUserId(Long userId);
    Optional<Review> findByUserAndRestaurant(User user, Restaurant restaurant);
    boolean existsByUserAndRestaurant(User user, Restaurant restaurant);
}