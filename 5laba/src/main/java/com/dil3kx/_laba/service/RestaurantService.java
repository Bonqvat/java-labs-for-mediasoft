package com.dil3kx._laba.service;

import com.dil3kx._laba.model.Restaurant;
import com.dil3kx._laba.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с ресторанами.
 * Требования:
 * - внедрить репозиторий,
 * - методы: save, remove, findAll.
 */
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * Конструктор для внедрения репозитория.
     */
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Сохраняет ресторан.
     */
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    /**
     * Удаляет ресторан по id.
     */
    public void remove(Long id) {
        restaurantRepository.remove(id);
    }

    /**
     * Возвращает список всех ресторанов.
     */
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}