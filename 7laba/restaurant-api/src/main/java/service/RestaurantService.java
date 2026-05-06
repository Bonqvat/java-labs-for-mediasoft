package com.example.restaurantapi.service;

import com.example.restaurantapi.dto.RestaurantRequestDTO;
import com.example.restaurantapi.dto.RestaurantResponseDTO;
import com.example.restaurantapi.entity.Restaurant;
import com.example.restaurantapi.mapper.RestaurantMapper;
import com.example.restaurantapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;

    public List<RestaurantResponseDTO> getAll() {
        return restaurantRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public RestaurantResponseDTO getById(Long id) {
        Restaurant r = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));
        return mapper.toResponse(r);
    }

    public RestaurantResponseDTO create(RestaurantRequestDTO dto) {
        Restaurant r = mapper.toEntity(dto);
        r = restaurantRepository.save(r);
        return mapper.toResponse(r);
    }

    public RestaurantResponseDTO update(Long id, RestaurantRequestDTO dto) {
        Restaurant r = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));
        r.setName(dto.name());
        r.setAddress(dto.address());
        r = restaurantRepository.save(r);
        return mapper.toResponse(r);
    }

    public void delete(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден");
        }
        restaurantRepository.deleteById(id);
    }

    /**
     * Поиск ресторанов с рейтингом не ниже заданного (Требование 3).
     * Здесь используется метод, сгенерированный по конвенции имени.
     * Можно заменить на findRestaurantsWithMinRating() для демонстрации @Query.
     */
    public List<RestaurantResponseDTO> findByRatingGreaterThanEqual(double minRating) {
        return restaurantRepository.findByRatingGreaterThanEqual(minRating)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}