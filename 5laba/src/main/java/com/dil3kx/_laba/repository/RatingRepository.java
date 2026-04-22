package com.dil3kx._laba.repository;

import com.dil3kx._laba.model.Rating;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Репозиторий для работы с оценками.
 * Требования:
 * - данные хранятся в приватном финальном поле типа List,
 * - методы: save, remove, findAll, findById (реализован как findByIds).
 *
 * Составной ключ: (visitorId, restaurantId). Один посетитель может оставить только одну оценку ресторану.
 */
@Repository
public class RatingRepository {

    // Хранилище оценок в памяти
    private final List<Rating> ratings = new ArrayList<>();

    /**
     * Сохраняет оценку. Если оценка от данного посетителя данному ресторану уже существует,
     * она заменяется новой.
     *
     * @param rating объект оценки
     * @return сохранённая оценка
     */
    public Rating save(Rating rating) {
        // Удаляем старую оценку от этого же посетителя этому же ресторану (если есть)
        ratings.removeIf(r -> r.getVisitorId().equals(rating.getVisitorId()) &&
                              r.getRestaurantId().equals(rating.getRestaurantId()));
        ratings.add(rating);
        return rating;
    }

    /**
     * Удаляет оценку по идентификаторам посетителя и ресторана.
     *
     * @param visitorId    id посетителя
     * @param restaurantId id ресторана
     */
    public void remove(Long visitorId, Long restaurantId) {
        ratings.removeIf(r -> r.getVisitorId().equals(visitorId) &&
                              r.getRestaurantId().equals(restaurantId));
    }

    /**
     * Возвращает список всех оценок (копию).
     *
     * @return список всех оценок
     */
    public List<Rating> findAll() {
        return new ArrayList<>(ratings);
    }

    /**
     * Находит все оценки, относящиеся к конкретному ресторану.
     * Используется для пересчёта среднего рейтинга ресторана.
     *
     * @param restaurantId id ресторана
     * @return список оценок данного ресторана
     */
    public List<Rating> findByRestaurantId(Long restaurantId) {
        return ratings.stream()
                .filter(r -> r.getRestaurantId().equals(restaurantId))
                .collect(Collectors.toList());
    }

    /**
     * Находит все оценки, оставленные конкретным посетителем.
     *
     * @param visitorId id посетителя
     * @return список оценок посетителя
     */
    public List<Rating> findByVisitorId(Long visitorId) {
        return ratings.stream()
                .filter(r -> r.getVisitorId().equals(visitorId))
                .collect(Collectors.toList());
    }

    /**
     * Ищет конкретную оценку по составному ключу (visitorId, restaurantId).
     * Соответствует требованию "findById".
     *
     * @param visitorId    id посетителя
     * @param restaurantId id ресторана
     * @return Optional с оценкой, если найдена
     */
    public Optional<Rating> findByIds(Long visitorId, Long restaurantId) {
        return ratings.stream()
                .filter(r -> r.getVisitorId().equals(visitorId) &&
                             r.getRestaurantId().equals(restaurantId))
                .findFirst();
    }
}