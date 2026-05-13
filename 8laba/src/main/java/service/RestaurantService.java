package com.mycompany.laba.service;

import com.mycompany.laba.exception.ResourceNotFoundException;
import com.mycompany.laba.exception.ValidationException;
import com.mycompany.laba.model.Restaurant;
import com.mycompany.laba.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant) {
        if (restaurantRepository.existsByName(restaurant.getName())) {
            throw new ValidationException("Restaurant already exists with name: " + restaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setName(restaurantDetails.getName());
        restaurant.setAddress(restaurantDetails.getAddress());
        restaurant.setCuisineType(restaurantDetails.getCuisineType());
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Restaurant not found with id: " + id);
        }
        restaurantRepository.deleteById(id);
    }
}