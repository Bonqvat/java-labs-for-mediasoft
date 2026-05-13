package com.mycompany.laba;

import com.mycompany.laba.model.Restaurant;
import com.mycompany.laba.model.Review;
import com.mycompany.laba.model.User;
import com.mycompany.laba.repository.RestaurantRepository;
import com.mycompany.laba.repository.ReviewRepository;
import com.mycompany.laba.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
class ReviewRepositoryTest {

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
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void testSaveAndFindReview() {
        User user = userRepository.save(new User("reviewer", "reviewer@test.com"));
        Restaurant restaurant = restaurantRepository.save(new Restaurant("ReviewRest", "Addr", "Type"));
        Review review = new Review(user, restaurant, 5, "Great!");
        Review saved = reviewRepository.save(review);
        assertThat(saved.getId()).isNotNull();
        assertThat(reviewRepository.existsByUserAndRestaurant(user, restaurant)).isTrue();
    }
}