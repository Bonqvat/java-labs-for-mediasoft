package com.example.restaurantapi.dto;

import jakarta.validation.constraints.NotBlank;

public record RestaurantRequestDTO(
    @NotBlank(message = "Название не может быть пустым")
    String name,

    @NotBlank(message = "Адрес обязателен")
    String address
) {}