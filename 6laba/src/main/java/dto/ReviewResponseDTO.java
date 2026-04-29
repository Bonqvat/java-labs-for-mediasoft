package com.example.restaurantapi.dto;

public record ReviewResponseDTO(Long id, String text, int rating, Long userId, Long restaurantId) {}