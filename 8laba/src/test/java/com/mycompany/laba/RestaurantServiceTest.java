package com.mycompany.laba;

import com.mycompany.laba.exception.ResourceNotFoundException;
import com.mycompany.laba.exception.ValidationException;
import com.mycompany.laba.model.Restaurant;
import com.mycompany.laba.repository.RestaurantRepository;
import com.mycompany.laba.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant("Tasty Cafe", "Main St", "Italian");
        restaurant.setId(1L);
    }

    @Test
    void createRestaurant_Success() {
        when(restaurantRepository.existsByName(restaurant.getName())).thenReturn(false);
        when(restaurantRepository.save(any())).thenReturn(restaurant);
        Restaurant created = restaurantService.createRestaurant(restaurant);
        assertNotNull(created);
        assertEquals("Tasty Cafe", created.getName());
    }

    @Test
    void createRestaurant_DuplicateName_ThrowsException() {
        when(restaurantRepository.existsByName(restaurant.getName())).thenReturn(true);
        assertThrows(ValidationException.class, () -> restaurantService.createRestaurant(restaurant));
    }

    @Test
    void getRestaurantById_NotFound_ThrowsException() {
        when(restaurantRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> restaurantService.getRestaurantById(99L));
    }
}