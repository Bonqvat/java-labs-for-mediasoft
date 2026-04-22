package com.dil3kx._laba.model;

import java.math.BigDecimal;

/**
 * Класс, представляющий ресторан.
 * Требования:
 * - id (типа Long),
 * - название,
 * - описание (может быть пустым),
 * - тип кухни (перечисление CuisineType),
 * - средний чек на человека (BigDecimal),
 * - оценка пользователей (типа BigDecimal, пересчитывается при добавлении/удалении оценок).
 */
public class Restaurant {

    private Long id;                          // Уникальный идентификатор ресторана
    private String name;                      // Название ресторана
    private String description;               // Описание (может быть null или пустой строкой)
    private CuisineType cuisineType;          // Тип кухни из перечисления
    private BigDecimal averageCheckPerPerson; // Средний чек на одного человека
    private BigDecimal averageRating;         // Средняя оценка ресторана

    // Пустой конструктор
    public Restaurant() {
    }

    /**
     * Конструктор со всеми полями.
     */
    public Restaurant(Long id, String name, String description, CuisineType cuisineType,
                      BigDecimal averageCheckPerPerson, BigDecimal averageRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cuisineType = cuisineType;
        this.averageCheckPerPerson = averageCheckPerPerson;
        this.averageRating = averageRating;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public BigDecimal getAverageCheckPerPerson() {
        return averageCheckPerPerson;
    }

    public void setAverageCheckPerPerson(BigDecimal averageCheckPerPerson) {
        this.averageCheckPerPerson = averageCheckPerPerson;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }
}