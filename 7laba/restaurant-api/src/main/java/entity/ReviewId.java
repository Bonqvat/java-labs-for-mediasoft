package com.example.restaurantapi.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReviewId implements Serializable {
    private Long userId;
    private Long restaurantId;

    public ReviewId() {}

    public ReviewId(Long userId, Long restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewId reviewId = (ReviewId) o;
        return Objects.equals(userId, reviewId.userId) &&
               Objects.equals(restaurantId, reviewId.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, restaurantId);
    }
}