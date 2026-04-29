package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.ReviewRequestDTO;
import com.example.restaurantapi.dto.ReviewResponseDTO;
import com.example.restaurantapi.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "Отзывы", description = "Управление отзывами")
public class ReviewController {
    private final ReviewService service;

    @GetMapping
    @Operation(summary = "Все отзывы")
    public List<ReviewResponseDTO> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Получить отзыв по ID")
    public ReviewResponseDTO getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    @Operation(summary = "Оставить отзыв")
    public ReviewResponseDTO create(@Valid @RequestBody ReviewRequestDTO dto) { return service.create(dto); }

    @PutMapping("/{id}")
    @Operation(summary = "Изменить отзыв")
    public ReviewResponseDTO update(@PathVariable Long id, @Valid @RequestBody ReviewRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить отзыв")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}