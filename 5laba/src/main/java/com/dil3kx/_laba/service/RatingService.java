package com.dil3kx._laba.service;

import com.dil3kx._laba.model.Rating;
import com.dil3kx._laba.model.Restaurant;
import com.dil3kx._laba.repository.RatingRepository;
import com.dil3kx._laba.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Сервис для работы с оценками.
 * Требования:
 * - внедрить репозитории RatingRepository и RestaurantRepository,
 * - методы: save, remove, findAll,
 * - при добавлении/удалении оценки пересчитать среднюю оценку ресторана.
 */
@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * Конструктор для внедрения зависимостей.
     */
    public RatingService(RatingRepository ratingRepository, RestaurantRepository restaurantRepository) {
        this.ratingRepository = ratingRepository;
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Сохраняет оценку и пересчитывает средний рейтинг соответствующего ресторана.
     */
    public Rating save(Rating rating) {
        Rating saved = ratingRepository.save(rating);
        updateRestaurantAverageRating(rating.getRestaurantId());
        return saved;
    }

    /**
     * Удаляет оценку по посетителю и ресторану и пересчитывает средний рейтинг ресторана.
     */
    public void remove(Long visitorId, Long restaurantId) {
        ratingRepository.remove(visitorId, restaurantId);
        updateRestaurantAverageRating(restaurantId);
    }

    /**
     * Возвращает список всех оценок.
     */
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    /**
     * Приватный метод для пересчёта среднего рейтинга ресторана.
     * Вычисляет среднее арифметическое всех оценок ресторана и обновляет поле averageRating.
     *
     * @param restaurantId идентификатор ресторана
     */
    private void updateRestaurantAverageRating(Long restaurantId) {
        restaurantRepository.findById(restaurantId).ifPresent(restaurant -> {
            List<Rating> ratings = ratingRepository.findByRestaurantId(restaurantId);
            if (ratings.isEmpty()) {
                // Если оценок нет, устанавливаем 0
                restaurant.setAverageRating(BigDecimal.ZERO);
            } else {
                // Вычисляем среднее значение
                double avg = ratings.stream()
                        .mapToInt(Rating::getRatingValue)
                        .average()
                        .orElse(0.0);
                // Округляем до двух знаков после запятой
                restaurant.setAverageRating(BigDecimal.valueOf(avg)
                        .setScale(2, RoundingMode.HALF_UP));
            }
            // Сохраняем обновлённый ресторан
            restaurantRepository.save(restaurant);
        });
    }
}