package com.example.restaurantapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
    @NotBlank(message = "Имя не может быть пустым")
    String name,

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    String email
) {}