package com.example.restaurantapi.mapper;

import com.example.restaurantapi.dto.ReviewRequestDTO;
import com.example.restaurantapi.dto.ReviewResponseDTO;
import com.example.restaurantapi.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, RestaurantMapper.class})
public interface ReviewMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "restaurantId", source = "restaurant.id")
    ReviewResponseDTO toResponse(Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    Review toEntity(ReviewRequestDTO dto);
}