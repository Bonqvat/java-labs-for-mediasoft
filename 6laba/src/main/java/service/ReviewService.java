package com.example.restaurantapi.service;

import com.example.restaurantapi.dto.ReviewRequestDTO;
import com.example.restaurantapi.dto.ReviewResponseDTO;
import com.example.restaurantapi.entity.Restaurant;
import com.example.restaurantapi.entity.Review;
import com.example.restaurantapi.entity.User;
import com.example.restaurantapi.mapper.ReviewMapper;
import com.example.restaurantapi.repository.RestaurantRepository;
import com.example.restaurantapi.repository.ReviewRepository;
import com.example.restaurantapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public List<ReviewResponseDTO> getAll() {
        return reviewRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ReviewResponseDTO getById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв не найден"));
        return mapper.toResponse(review);
    }

    public ReviewResponseDTO create(ReviewRequestDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));

        Review review = mapper.toEntity(dto);
        review.setUser(user);
        review.setRestaurant(restaurant);
        review = reviewRepository.save(review);
        return mapper.toResponse(review);
    }

    public ReviewResponseDTO update(Long id, ReviewRequestDTO dto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв не найден"));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден"));
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ресторан не найден"));

        review.setText(dto.text());
        review.setRating(dto.rating());
        review.setUser(user);
        review.setRestaurant(restaurant);
        review = reviewRepository.save(review);
        return mapper.toResponse(review);
    }

    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв не найден");
        }
        reviewRepository.deleteById(id);
    }
}