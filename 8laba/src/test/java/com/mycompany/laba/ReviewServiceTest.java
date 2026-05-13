package com.mycompany.laba;

import com.mycompany.laba.exception.DuplicateReviewException;
import com.mycompany.laba.model.Review;
import com.mycompany.laba.model.User;
import com.mycompany.laba.model.Restaurant;
import com.mycompany.laba.repository.ReviewRepository;
import com.mycompany.laba.service.UserService;
import com.mycompany.laba.service.RestaurantService;
import com.mycompany.laba.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private UserService userService;
    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private ReviewService reviewService;

    private User user;
    private Restaurant restaurant;
    private Review review;

    @BeforeEach
    void setUp() {
        user = new User("user", "user@mail.com");
        user.setId(1L);
        restaurant = new Restaurant("Pizza Place", "Address", "Italian");
        restaurant.setId(1L);
        review = new Review(user, restaurant, 5, "Great!");
        review.setId(1L);
    }

    @Test
    void createReview_Success() {
        when(userService.getUserById(1L)).thenReturn(user);
        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurant);
        when(reviewRepository.existsByUserAndRestaurant(user, restaurant)).thenReturn(false);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review created = reviewService.createReview(1L, 1L, review);
        assertNotNull(created);
        assertEquals(5, created.getRating());
    }

    @Test
    void createReview_Duplicate_ThrowsException() {
        when(userService.getUserById(1L)).thenReturn(user);
        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurant);
        when(reviewRepository.existsByUserAndRestaurant(user, restaurant)).thenReturn(true);

        assertThrows(DuplicateReviewException.class, () -> reviewService.createReview(1L, 1L, review));
    }
}