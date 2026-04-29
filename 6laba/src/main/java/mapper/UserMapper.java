package com.example.restaurantapi.mapper;

import com.example.restaurantapi.dto.UserRequestDTO;
import com.example.restaurantapi.dto.UserResponseDTO;
import com.example.restaurantapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toResponse(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDTO dto);
}