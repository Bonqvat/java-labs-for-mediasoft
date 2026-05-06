package com.example.restaurantapi.dto;

import jakarta.validation.constraints.*;

public record ReviewRequestDTO(
    @NotBlank(message = "Текст отзыва обязателен")
    String text,

    @Min(value = 1, message = "Оценка должна быть от 1 до 5")
    @Max(value = 5, message = "Оценка должна быть от 1 до 5")
    int rating,

    @NotNull(message = "ID пользователя обязателен")
    Long userId,

    @NotNull(message = "ID ресторана обязателен")
    Long restaurantId
) {}