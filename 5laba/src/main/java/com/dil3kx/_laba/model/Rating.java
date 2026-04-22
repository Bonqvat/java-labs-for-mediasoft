package com.dil3kx._laba.model;

/**
 * Класс, представляющий оценку, оставленную посетителем ресторану.
 * Требования:
 * - id посетителя,
 * - id ресторана,
 * - оценка (int),
 * - текст отзыва (может быть пустым).
 * 
 * Связь с Visitor и Restaurant осуществляется по идентификаторам.
 */
public class Rating {

    private Long visitorId;        // ID посетителя, оставившего оценку
    private Long restaurantId;     // ID оцениваемого ресторана
    private int ratingValue;       // Числовая оценка (например, от 1 до 5)
    private String reviewText;     // Текст отзыва (может быть null)

    // Пустой конструктор
    public Rating() {
    }

    /**
     * Конструктор со всеми полями.
     */
    public Rating(Long visitorId, Long restaurantId, int ratingValue, String reviewText) {
        this.visitorId = visitorId;
        this.restaurantId = restaurantId;
        this.ratingValue = ratingValue;
        this.reviewText = reviewText;
    }

    // Геттеры и сеттеры
    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}