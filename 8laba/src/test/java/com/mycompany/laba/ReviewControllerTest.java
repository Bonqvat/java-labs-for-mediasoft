package com.mycompany.laba;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.laba.controller.ReviewController;
import com.mycompany.laba.exception.DuplicateReviewException;
import com.mycompany.laba.model.Review;
import com.mycompany.laba.model.User;
import com.mycompany.laba.model.Restaurant;
import com.mycompany.laba.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createReview_Success() throws Exception {
        User user = new User("u", "u@m.com");
        user.setId(1L);
        Restaurant restaurant = new Restaurant("R", "A", "Type");
        restaurant.setId(1L);
        Review review = new Review(user, restaurant, 4, "Good");
        review.setId(10L);
        when(reviewService.createReview(eq(1L), eq(1L), any(Review.class))).thenReturn(review);

        mockMvc.perform(post("/api/reviews?userId=1&restaurantId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(review)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rating").value(4));
    }

    @Test
    void getReviewsByRestaurant_ShouldReturnList() throws Exception {
        User user = new User("u", "u@m.com");
        user.setId(1L);
        Restaurant restaurant = new Restaurant("R", "A", "Type");
        restaurant.setId(1L);
        Review review = new Review(user, restaurant, 4, "Good");
        review.setId(10L);
        when(reviewService.getReviewsByRestaurant(1L)).thenReturn(List.of(review));

        mockMvc.perform(get("/api/reviews/restaurant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rating").value(4));
    }

    @Test
    void updateReview_ShouldReturnUpdatedReview() throws Exception {
        User user = new User("u", "u@m.com");
        user.setId(1L);
        Restaurant restaurant = new Restaurant("R", "A", "Type");
        restaurant.setId(1L);
        Review updated = new Review(user, restaurant, 5, "Excellent");
        updated.setId(10L);
        when(reviewService.updateReview(eq(10L), any(Review.class))).thenReturn(updated);

        mockMvc.perform(put("/api/reviews/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "rating": 5,
                                    "comment": "Excellent"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(5));
    }

    @Test
    void deleteReview_ShouldReturn204() throws Exception {
        doNothing().when(reviewService).deleteReview(10L);
        mockMvc.perform(delete("/api/reviews/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    void createDuplicateReview_Returns400() throws Exception {
        Review review = new Review(null, null, 5, "Nice");
        when(reviewService.createReview(eq(1L), eq(1L), any(Review.class)))
                .thenThrow(new DuplicateReviewException("User already reviewed this restaurant"));

        mockMvc.perform(post("/api/reviews?userId=1&restaurantId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "rating": 5,
                                    "comment": "Nice"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }
}