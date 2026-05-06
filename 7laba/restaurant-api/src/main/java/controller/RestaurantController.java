package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.RestaurantRequestDTO;
import com.example.restaurantapi.dto.RestaurantResponseDTO;
import com.example.restaurantapi.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Tag(name = "Рестораны", description = "Управление ресторанами")
public class RestaurantController {
    private final RestaurantService service;

    @GetMapping
    @Operation(summary = "Список ресторанов")
    public List<RestaurantResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить ресторан по ID")
    public RestaurantResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Добавить ресторан")
    public RestaurantResponseDTO create(@Valid @RequestBody RestaurantRequestDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить ресторан")
    public RestaurantResponseDTO update(@PathVariable Long id,
                                        @Valid @RequestBody RestaurantRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить ресторан")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Фильтрация ресторанов по минимальному рейтингу (Требование 3).
     * Пример: /api/restaurants/filter?minRating=4.5
     */
    @GetMapping("/filter")
    @Operation(summary = "Рестораны с рейтингом не ниже указанного")
    public List<RestaurantResponseDTO> filterByRating(@RequestParam double minRating) {
        return service.findByRatingGreaterThanEqual(minRating);
    }
}