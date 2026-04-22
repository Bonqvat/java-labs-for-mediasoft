package com.dil3kx._laba.repository;

import com.dil3kx._laba.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с данными о ресторанах.
 * Требования:
 * - данные хранятся в приватном финальном поле типа List,
 * - методы: save, remove, findAll.
 */
@Repository
public class RestaurantRepository {

    // Хранилище ресторанов в памяти
    private final List<Restaurant> restaurants = new ArrayList<>();
    // Генератор идентификаторов
    private long nextId = 1L;

    /**
     * Сохраняет ресторан. Если id == null, генерирует новый id и добавляет в список.
     * Иначе обновляет существующий.
     *
     * @param restaurant объект ресторана
     * @return сохранённый объект с id
     */
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.setId(nextId++);
            restaurants.add(restaurant);
        } else {
            deleteById(restaurant.getId());
            restaurants.add(restaurant);
        }
        return restaurant;
    }

    /**
     * Удаляет ресторан по идентификатору.
     *
     * @param id идентификатор удаляемого ресторана
     */
    public void remove(Long id) {
        restaurants.removeIf(r -> r.getId().equals(id));
    }

    /**
     * Возвращает список всех ресторанов (копию внутреннего списка).
     *
     * @return список ресторанов
     */
    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants);
    }

    /**
     * Вспомогательный метод для поиска ресторана по id.
     *
     * @param id идентификатор
     * @return Optional с рестораном, если найден
     */
    public Optional<Restaurant> findById(Long id) {
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    // Внутренний метод удаления по id
    private void deleteById(Long id) {
        restaurants.removeIf(r -> r.getId().equals(id));
    }
}