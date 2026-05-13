package com.mycompany.laba;

import com.mycompany.laba.model.Restaurant;
import com.mycompany.laba.model.Review;
import com.mycompany.laba.model.User;
import com.mycompany.laba.service.RestaurantService;
import com.mycompany.laba.service.ReviewService;
import com.mycompany.laba.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
class ReviewServiceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @Test
    void createAndRetrieveReview() {
        User user = userService.createUser(new User("reviewer_int", "reviewer_int@test.com"));
        Restaurant restaurant = restaurantService.createRestaurant(new Restaurant("Int Restaurant", "Addr", "Cuisine"));
        Review review = new Review(user, restaurant, 5, "Integration test review");
        Review saved = reviewService.createReview(user.getId(), restaurant.getId(), review);
        assertThat(saved.getId()).isNotNull();

        var reviews = reviewService.getReviewsByRestaurant(restaurant.getId());
        assertThat(reviews).hasSize(1);
        assertThat(reviews.get(0).getComment()).isEqualTo("Integration test review");
    }
}