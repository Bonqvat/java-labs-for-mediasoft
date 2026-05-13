package com.mycompany.laba;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.laba.controller.RestaurantController;
import com.mycompany.laba.exception.ResourceNotFoundException;
import com.mycompany.laba.model.Restaurant;
import com.mycompany.laba.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createRestaurant_Valid_ReturnsCreated() throws Exception {
        Restaurant restaurant = new Restaurant("Sushi Bar", "Downtown", "Japanese");
        restaurant.setId(1L);
        when(restaurantService.createRestaurant(any())).thenReturn(restaurant);

        mockMvc.perform(post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sushi Bar"));
    }

    @Test
    void getRestaurantById_ShouldReturnRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant("Sushi Bar", "Downtown", "Japanese");
        restaurant.setId(1L);
        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurant);

        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sushi Bar"));
    }

    @Test
    void updateRestaurant_ShouldReturnUpdatedRestaurant() throws Exception {
        Restaurant updated = new Restaurant("Burger King", "Uptown", "Fast Food");
        updated.setId(1L);
        when(restaurantService.updateRestaurant(eq(1L), any(Restaurant.class))).thenReturn(updated);

        mockMvc.perform(put("/api/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Burger King",
                                    "address": "Uptown",
                                    "cuisineType": "Fast Food"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Burger King"));
    }

    @Test
    void deleteRestaurant_Existing_ReturnsNoContent() throws Exception {
        doNothing().when(restaurantService).deleteRestaurant(1L);
        mockMvc.perform(delete("/api/restaurants/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteRestaurant_NotFound_Returns404() throws Exception {
        doThrow(new ResourceNotFoundException("Restaurant not found"))
                .when(restaurantService).deleteRestaurant(99L);
        mockMvc.perform(delete("/api/restaurants/99"))
                .andExpect(status().isNotFound());
    }
}