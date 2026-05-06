package com.example.restaurantapi.dto;

public record ReviewResponseDTO(String text, int rating, Long userId, Long restaurantId) {}