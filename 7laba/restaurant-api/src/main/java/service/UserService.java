package com.example.restaurantapi.service;

import com.example.restaurantapi.dto.UserRequestDTO;
import com.example.restaurantapi.dto.UserResponseDTO;
import com.example.restaurantapi.entity.User;
import com.example.restaurantapi.mapper.UserMapper;
import com.example.restaurantapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        return userMapper.toResponse(user);
    }

    public UserResponseDTO create(UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        user.setName(dto.name());
        user.setEmail(dto.email());
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден");
        }
        userRepository.deleteById(id);
    }
}