package com.example.restaurantapi.service;

import com.example.restaurantapi.dto.ReviewRequestDTO;
import com.example.restaurantapi.dto.ReviewResponseDTO;
import com.example.restaurantapi.entity.*;
import com.example.restaurantapi.mapper.ReviewMapper;
import com.example.restaurantapi.repository.RestaurantRepository;
import com.example.restaurantapi.repository.ReviewRepository;
import com.example.restaurantapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewMapper mapper;

    // Возвращает все отзывы (без пагинации)
    public List<ReviewResponseDTO> getAll() {
        return reviewRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // Получение отзыва по составному ключу
    public ReviewResponseDTO getById(Long userId, Long restaurantId) {
        ReviewId id = new ReviewId(userId, restaurantId);
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв не найден"));
        return mapper.toResponse(review);
    }

    // Создание нового отзыва
    public ReviewResponseDTO create(ReviewRequestDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));

        // Проверка на уникальность: один пользователь – один отзыв на ресторан
        ReviewId reviewId = new ReviewId(user.getId(), restaurant.getId());
        if (reviewRepository.existsById(reviewId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Вы уже оставили отзыв на этот ресторан");
        }

        Review review = new Review(user, restaurant, dto.text(), dto.rating());
        review = reviewRepository.save(review);
        return mapper.toResponse(review);
    }

    // Обновление отзыва по составному ключу
    public ReviewResponseDTO update(Long userId, Long restaurantId, ReviewRequestDTO dto) {
        ReviewId id = new ReviewId(userId, restaurantId);
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв не найден"));

        // Пользователь и ресторан менять нельзя, обновляем только текст и оценку
        review.setText(dto.text());
        review.setRating(dto.rating());
        review = reviewRepository.save(review);
        return mapper.toResponse(review);
    }

    // Удаление отзыва по составному ключу
    public void delete(Long userId, Long restaurantId) {
        ReviewId id = new ReviewId(userId, restaurantId);
        if (!reviewRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв не найден");
        }
        reviewRepository.deleteById(id);
    }

    /**
     * Постраничный вывод отзывов с сортировкой (Требование 2).
     */
    public Page<ReviewResponseDTO> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(mapper::toResponse);
    }
}