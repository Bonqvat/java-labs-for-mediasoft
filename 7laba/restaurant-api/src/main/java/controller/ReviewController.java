package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.ReviewRequestDTO;
import com.example.restaurantapi.dto.ReviewResponseDTO;
import com.example.restaurantapi.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Отзывы", description = "Управление отзывами")
public class ReviewController {
    private final ReviewService service;

    @GetMapping
    @Operation(summary = "Все отзывы с пагинацией и сортировкой")
    public Page<ReviewResponseDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "rating,asc") String sort) {
        String[] parts = sort.split(",");
        String field = parts[0];
        Sort.Direction direction = Sort.Direction.ASC;
        if (parts.length > 1 && parts[1].equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, field));
        return service.findAll(pageable);
    }

    @GetMapping("/byIds")
    @Operation(summary = "Получить отзыв по ID пользователя и ресторана")
    public ReviewResponseDTO getById(@RequestParam Long userId,
                                     @RequestParam Long restaurantId) {
        return service.getById(userId, restaurantId);
    }

    @PostMapping
    @Operation(summary = "Оставить отзыв")
    public ReviewResponseDTO create(@Valid @RequestBody ReviewRequestDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/byIds")
    @Operation(summary = "Изменить отзыв")
    public ReviewResponseDTO update(@RequestParam Long userId,
                                    @RequestParam Long restaurantId,
                                    @Valid @RequestBody ReviewRequestDTO dto) {
        return service.update(userId, restaurantId, dto);
    }

    @DeleteMapping("/byIds")
    @Operation(summary = "Удалить отзыв")
    public ResponseEntity<Void> delete(@RequestParam Long userId,
                                       @RequestParam Long restaurantId) {
        service.delete(userId, restaurantId);
        return ResponseEntity.noContent().build();
    }
}