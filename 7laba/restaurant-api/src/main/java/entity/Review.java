package com.example.restaurantapi.entity;

import jakarta.persistence.*;

@Entity
public class Review {

    @EmbeddedId
    private ReviewId id;

    private String text;
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Review() {}

    // Конструктор, автоматически формирующий составной ключ
    public Review(User user, Restaurant restaurant, String text, int rating) {
        this.user = user;
        this.restaurant = restaurant;
        this.text = text;
        this.rating = rating;
        this.id = new ReviewId(user.getId(), restaurant.getId());
    }

    public ReviewId getId() { return id; }
    public void setId(ReviewId id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
}