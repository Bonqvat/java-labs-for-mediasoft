package com.example.restaurantapi.mapper;

import com.example.restaurantapi.dto.RestaurantRequestDTO;
import com.example.restaurantapi.dto.RestaurantResponseDTO;
import com.example.restaurantapi.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantResponseDTO toResponse(Restaurant restaurant);

    @Mapping(target = "id", ignore = true)
    Restaurant toEntity(RestaurantRequestDTO dto);
}