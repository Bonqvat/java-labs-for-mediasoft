package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.UserRequestDTO;
import com.example.restaurantapi.dto.UserResponseDTO;
import com.example.restaurantapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Управление посетителями")
public class UserController {
    private final UserService service;

    @GetMapping
    @Operation(summary = "Получить список всех пользователей")
    public List<UserResponseDTO> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по ID")
    public UserResponseDTO getById(@PathVariable Long id) { return service.getById(id); }

    @PostMapping
    @Operation(summary = "Создать нового пользователя")
    public UserResponseDTO create(@Valid @RequestBody UserRequestDTO dto) { return service.create(dto); }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные пользователя")
    public UserResponseDTO update(@PathVariable Long id, @Valid @RequestBody UserRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}